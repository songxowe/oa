package com.softfactory.core.service;

import com.softfactory.core.dao.MeetingMapper;
import com.softfactory.core.util.ActivitiUtil;
import com.softfactory.core.util.Pager;
import com.softfactory.pojo.Meeting;
import com.softfactory.pojo.PubMeetingRoom;
import com.softfactory.pojo.User;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 会议申请service
 */
@Service("meetingService")
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
public class MeetingService {

    @Resource(name = "meetingMapper")
    private MeetingMapper meetingMapper;
    @Resource(name = "activitiUtil")
    private ActivitiUtil activitiUtil;
    @Resource(name = "pubMeetingRoomService")
    private PubMeetingRoomService pubMeetingRoomService;

    /**
     * 增加会议申请，并启动会议申请流程
     * @param meeting
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void add(Meeting meeting, User user) {
        meetingMapper.add(meeting);

        Integer id = meetingMapper.getLastId();
        //启动会议申请流程实例
        activitiUtil.startPI(id,user.getId(),"meetingProcess");

        List<Task> tasks = activitiUtil.getTasksByAssignee(0,1,user.getUserTrueName(),"meetingProcess");
        activitiUtil.finishTask(tasks.get(0).getId());

        String taskId = activitiUtil.getTasksByAssignee(0,1,"张李境","meetingProcess").get(0).getId();

        meetingMapper.updateTaskId(taskId,id);

        PubMeetingRoom pubMeetingRoom = pubMeetingRoomService.find(meeting.getMeetingRoom().getMrId());

        pubMeetingRoom.setUseingFalg(1);

        pubMeetingRoomService.modify(pubMeetingRoom);

    }

    /**
     * 更新taskId
     * @param taskId
     * @param id
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Integer updateTaskId(String taskId,Integer id) {
        return meetingMapper.updateTaskId(taskId,id);
    }


    /**
     * 更新审核步骤
     * @param status
     * @param id
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Integer updateStatus(String status,Integer id) {
        return meetingMapper.updateStatus(status,id);
    }

    /**
     * 分页
     * @param pageNo
     * @param pageSize
     * @param sort
     * @param order
     * @param proposer
     * @param MTopic
     * @param beginDate
     * @param endDate
     * @return
     */
    public Pager<Meeting> find(Integer pageNo,
                               Integer pageSize,
                               String sort,
                               String order,
                               String proposer,
                               String MTopic,
                               Date beginDate,
                               Date endDate) {
        Pager<Meeting> pager = new Pager<>();
        pager.setRows(meetingMapper.findPager(pageNo,pageSize,sort,order,proposer,MTopic,beginDate,endDate));
        pager.setTotal(meetingMapper.getTotal(proposer,MTopic,beginDate,endDate));
        return pager;
    }

    /**
     * 查单个会议申请
     * @param id
     * @return
     */
    public Meeting findById(Integer id) {
        return meetingMapper.findById(id);
    }

}
