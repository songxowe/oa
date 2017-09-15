package com.softfactory.core.service;

import com.softfactory.core.dao.LeaveBillMapper;
import com.softfactory.core.util.ActivitiUtil;
import com.softfactory.core.util.Pager;
import com.softfactory.pojo.LeaveBill;
import com.softfactory.pojo.User;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("leaveBillService")
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
public class LeaveBillService {

    @Resource(name = "leaveBillMapper")
    private LeaveBillMapper leaveBillMapper;
    @Resource(name = "activitiUtil")
    private ActivitiUtil activitiUtil;
    @Resource(name = "userService")
    private UserService userService;

    /**
     * 增加请假表并启动请假流程，完成请假任务
     * @param leaveBill
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void add(LeaveBill leaveBill, User user) {
        leaveBillMapper.add(leaveBill);
        Integer leaveId = leaveBillMapper.getLastId();
        activitiUtil.startPI(leaveId,user.getId(), "leaveProcess");
        List<Task> tasks = activitiUtil.getTasksByAssignee(0,1,user.getUserTrueName(),"leaveProcess");

        activitiUtil.finishTask(tasks.get(0).getId());

        User manager = userService.findById(user.getManagerId());

        String taskId = activitiUtil.getTasksByAssignee(0,1,manager.getUserTrueName(),"leaveProcess").get(0).getId();

        leaveBillMapper.updateTaskId(taskId,leaveId);

    }

    public Pager<LeaveBill> find(Integer pageNo, Integer pageSize, String sort, String order, Date beginDate, Date endDate, String proposer) {
        Pager<LeaveBill> pager = new Pager<>();
        pager.setRows(leaveBillMapper.findPager(pageNo,pageSize,sort,order,beginDate,endDate,proposer));
        pager.setTotal(leaveBillMapper.getTotal(beginDate,endDate,proposer));
        return pager;
    }

    public Integer getLastId() {
        return leaveBillMapper.getLastId();
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Integer updateStatus(String status,Integer id) {
        return leaveBillMapper.updateStatus(status,id);
    }


    public LeaveBill findById(Integer id) {
        return leaveBillMapper.findById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Integer updateTaskId(String taskId,Integer id){
        return leaveBillMapper.updateTaskId(taskId,id);
    }

}
