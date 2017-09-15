package com.softfactory.core.service;

import com.softfactory.core.dao.FlowVehicleMapper;
import com.softfactory.core.util.ActivitiUtil;
import com.softfactory.core.util.Pager;
import com.softfactory.pojo.FlowVehicle;
import com.softfactory.pojo.VehicleUsage;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("flowVehicleService")
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
public class FlowVehicleService {
    @Resource(name = "flowVehicleMapper")
    private FlowVehicleMapper flowVehicleMapper;
    @Resource(name = "activitiUtil")
    private ActivitiUtil activitiUtil;
    @Resource(name = "vehicleUsageService")
    private VehicleUsageService vehicleUsageService;

    /**
     * 查询审批记录
     * @param vuId
     * @return
     */
    public List<FlowVehicle> find(Integer vuId) {
        return flowVehicleMapper.findByVuId(vuId);
    }

    /**
     * 车辆申请审批任务列表
     * @param pageNo
     * @param pageSize
     * @param userId
     * @param pdKey
     * @return
     */
    public Pager<Task> find(Integer pageNo, Integer pageSize, String userId, String pdKey) {
        Pager<Task> pager = new Pager<>();
        pager.setTotal(activitiUtil.getTaskCount(userId,pdKey));
        pager.setRows(activitiUtil.getTasksByAssignee(pageNo,pageSize,userId,pdKey));
        return pager;
    }

    /**
     * 完成车辆审批任务
     * @param taskId
     * @param userId
     * @param checkIdea
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void completeTask(String taskId,String userId,String checkIdea) {

        FlowVehicle flowVehicle = new FlowVehicle();
        flowVehicle.setChecker(userId);
        flowVehicle.setCheckIdea(checkIdea);
        flowVehicle.setCheckStatus("通过");

        String businessKey = activitiUtil.getBusinessKeyByTaskId(taskId);

        VehicleUsage vehicleUsage = vehicleUsageService.find(Integer.parseInt(businessKey));

        flowVehicle.setVuId(vehicleUsage.getId());

        ProcessInstance pi = activitiUtil.finishTask(taskId);

        if(pi == null) {
            vehicleUsageService.updateStatus("审批完成",vehicleUsage.getId());
        }else {
            vehicleUsageService.updateTaskId(activitiUtil.getTasksByAssignee(0,1,"严新贵","carProcess").get(0).getId(),vehicleUsage.getId());
        }

        flowVehicleMapper.add(flowVehicle);

    }



}
