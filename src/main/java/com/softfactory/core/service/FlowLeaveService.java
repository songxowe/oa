package com.softfactory.core.service;

import com.softfactory.core.dao.FlowLeaveMapper;
import com.softfactory.core.util.ActivitiUtil;
import com.softfactory.core.util.Pager;
import com.softfactory.pojo.FlowLeave;
import com.softfactory.pojo.LeaveBill;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("flowLeaveService")
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class FlowLeaveService {
    @Resource(name = "activitiUtil")
    private ActivitiUtil activitiUtil;
    @Resource(name = "flowLeaveMapper")
    private FlowLeaveMapper flowLeaveMapper;
    @Resource(name = "leaveBillService")
    private LeaveBillService leaveBillService;

    public List<FlowLeave> findByLeaveId(Integer leaveId) {
        return flowLeaveMapper.findByLeaveId(leaveId);
    }

    /**
     * 根据登录用户查看审批任务
     *
     * @param pageNo
     * @param pageSize
     * @param userId
     * @return
     */
    public Pager<Task> find(Integer pageNo, Integer pageSize, String userId, String pdKey) {
        Pager<Task> pager = new Pager<>();
        pager.setTotal(activitiUtil.getTaskCount(userId, pdKey));
        pager.setRows(activitiUtil.getTasksByAssignee(pageNo, pageSize, userId, pdKey));
        return pager;
    }

    /**
     * 当前登录人办理审批任务
     *
     * @param taskId
     * @param userId
     * @param checkIdea
     * @param checkStatus
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void completeTask(String taskId, String userId, String checkIdea) {
        FlowLeave flowLeave = new FlowLeave();
        flowLeave.setChecker(userId);
        flowLeave.setCheckIdea(checkIdea);

        String businessKey = activitiUtil.getBusinessKeyByTaskId(taskId);
        LeaveBill leaveBill = leaveBillService.findById(Integer.parseInt(businessKey));
        flowLeave.setLeaveId(leaveBill.getLeaveId());


        ProcessInstance pi = activitiUtil.finishTask(taskId);

        flowLeave.setCheckStatus("通过");

        if (pi == null) {
            leaveBillService.updateStatus("审批完成", leaveBill.getLeaveId());
        } else {
            leaveBillService.updateTaskId(activitiUtil.getTasksByAssignee(0, 1, "吴建辉", "leaveProcess").get(0).getId(), leaveBill.getLeaveId());
        }

        flowLeaveMapper.add(flowLeave);

    }

}
