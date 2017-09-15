package com.softfactory.core.service;

import com.softfactory.core.dao.FlowMeetingMapper;
import com.softfactory.core.util.ActivitiUtil;
import com.softfactory.core.util.Pager;
import com.softfactory.pojo.FlowMeeting;
import com.softfactory.pojo.Meeting;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("flowMeetingService")
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
public class FlowMeetingService {
    @Resource(name = "activitiUtil")
    private ActivitiUtil activitiUtil;
    @Resource(name = "flowMeetingMapper")
    private FlowMeetingMapper flowMeetingMapper;
    @Resource(name = "meetingService")
    private MeetingService meetingService;

    public List<FlowMeeting> find(Integer muId) {
        return flowMeetingMapper.findByMeetingId(muId);
    }

    /**
     * 会议室管理员登录后查看审批任务
     * @param pageNo
     * @param pageSize
     * @param userId
     * @return
     */
    public Pager<Task> find(Integer pageNo,Integer pageSize,String userId,String pdKey) {
        Pager<Task> pager = new Pager<>();
        pager.setTotal(activitiUtil.getTaskCount(userId,pdKey));
        pager.setRows(activitiUtil.getTasksByAssignee(pageNo,pageSize,userId,pdKey));
        return pager;
    }

    /**
     * 会议室管理员完成审批任务
     * @param taskId
     * @param userId
     * @param checkIdea
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void completeTask(String taskId,String userId,String checkIdea) {
        FlowMeeting flowMeeting = new FlowMeeting();
        flowMeeting.setChecker(userId);
        flowMeeting.setCheckIdea(checkIdea);
        flowMeeting.setCheckStatus("通过");

        String businessKey = activitiUtil.getBusinessKeyByTaskId(taskId);

        Meeting meeting = meetingService.findById(Integer.parseInt(businessKey));
        flowMeeting.setMuId(meeting.getId());

        activitiUtil.finishTask(taskId);

        meetingService.updateStatus("审批完成",meeting.getId());
        meetingService.updateTaskId(taskId,meeting.getId());
        flowMeetingMapper.add(flowMeeting);


    }
}
