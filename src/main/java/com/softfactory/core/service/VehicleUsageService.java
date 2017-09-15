package com.softfactory.core.service;

import com.softfactory.core.dao.VehicleUsageMapper;
import com.softfactory.core.util.ActivitiUtil;
import com.softfactory.core.util.Pager;
import com.softfactory.pojo.*;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("vehicleUsageService")
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
public class VehicleUsageService {

    @Resource(name = "vehicleUsageMapper")
    private VehicleUsageMapper vehicleUsageMapper;
    @Resource(name = "activitiUtil")
    private ActivitiUtil activitiUtil;
    @Resource(name = "pubVehicleService")
    private PubVehicleService pubVehicleService;
    @Resource(name = "userService")
    private UserService userService;


    /**
     * 增加车辆申请，并启动车辆申请流程，完成申请任务
     * @param vehicleUsage
     * @param user
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void add(VehicleUsage vehicleUsage, User user) {
        vehicleUsageMapper.add(vehicleUsage);

        Integer id = vehicleUsageMapper.getLastId();
        //启动车辆申请流程实例
        activitiUtil.startPI(id,user.getId(),"carProcess");

        List<Task> tasks = activitiUtil.getTasksByAssignee(0,1,user.getUserTrueName(),"carProcess");
        activitiUtil.finishTask(tasks.get(0).getId());

        User manager = userService.findById(user.getManagerId());

        String taskId = activitiUtil.getTasksByAssignee(0,1,manager.getUserTrueName(),"carProcess").get(0).getId();

        vehicleUsageMapper.updateTaskId(taskId,id);

        PubVehicle pubVehicle = pubVehicleService.find(vehicleUsage.getPubVehicle().getVId());


        pubVehicle.setUseingFalg(1);

        pubVehicleService.modify(pubVehicle);
    }


    public Pager<VehicleUsage> find(Integer pageNo,
                                    Integer pageSize,
                                    String sort,
                                    String order,
                                    String proposer,
                                    Date beginDate,
                                    Date endDate) {
        Pager<VehicleUsage> pager = new Pager<>();
        pager.setRows(vehicleUsageMapper.findPager(pageNo,pageSize,sort,order,proposer,beginDate,endDate));
        pager.setTotal(vehicleUsageMapper.getTotal(proposer,beginDate,endDate));
        return pager;
    }

    public VehicleUsage find(Integer id) {
        return vehicleUsageMapper.findById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Integer updateTaskId(String taskId,Integer id) {
        return vehicleUsageMapper.updateTaskId(taskId,id);
    }
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Integer updateStatus(String status,Integer id) {
        return vehicleUsageMapper.updateStatus(status,id);
    }

}
