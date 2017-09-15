package com.softfactory.core.controller;


import com.softfactory.core.service.MeetingService;
import com.softfactory.core.util.*;
import com.softfactory.pojo.Meeting;
import com.softfactory.pojo.PubMeetingRoom;
import com.softfactory.pojo.User;
import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 会议申请处理
 */
@Controller
public class MeetingController {
    @Resource(name = "meetingService")
    private MeetingService meetingService;
    @Resource(name = "activitiUtil")
    private ActivitiUtil activitiUtil;


    /**
     * 会议申请列表
     *
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @param proposer
     * @param MTopic
     * @param beginDate
     * @param endDate
     * @return
     */
    @RequestMapping(value = "meetingList", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String list(@RequestParam(value = "page") Integer page,
                @RequestParam(value = "rows") Integer rows,
                @RequestParam(value = "sort") String sort,
                @RequestParam(value = "order") String order,
                @RequestParam(value = "proposer", required = false) String proposer,
                @RequestParam(value = "MTopic", required = false) String MTopic,
                @RequestParam(value = "beginDate", required = false)
                @DateTimeFormat(pattern = "yyyy-MM-dd") Date beginDate,
                @RequestParam(value = "endDate", required = false)
                @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        Integer pageNo = (page - 1) * rows;
        Integer pageSize = rows;

        Pager<Meeting> pager = meetingService.find(pageNo, pageSize, sort, order, proposer, MTopic, beginDate, endDate);
        JsonConfig jc = new JsonConfig();
        jc.registerJsonValueProcessor(PubMeetingRoom.class, new JsonMeetingRoomValueProcessor());
        jc.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd HH:mm:ss"));
        JSON json = JSONSerializer.toJSON(pager, jc);
        return json.toString();

    }

    /**
     * 跳转会议申请页面
     *
     * @return
     */
    @RequestMapping("applyMeeting")
    public String applyMeeting() {
        return "workflow/applyMeeting";
    }

    /**
     * 会议申请
     *
     * @return
     */
    @RequestMapping(value = "addMeeting", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String addMeeting(@RequestParam(value = "proposer") String proposer,
                      @RequestParam(value = "meetingRoom") Integer meetingRoom,
                      @RequestParam(value = "MTopic") String MTopic,
                      @RequestParam(value = "MStart")
                      @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date MStart,
                      @RequestParam(value = "MEnd")
                      @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date MEnd,
                      @RequestParam(value = "MDesc", required = false) String MDesc,
                      @RequestParam(value = "MAttendee", required = false) String MAttendee,
                      HttpServletRequest request) {

        //System.out.println(proposer+" "+MTopic+" "+MStart+" "+MEnd+".......................");
        User user = (User) request.getSession().getAttribute(Constants.USER_IN_SESSION);

        Meeting meeting = new Meeting();
        PubMeetingRoom pubMeetingRoom = new PubMeetingRoom();
        pubMeetingRoom.setMrId(meetingRoom);
        meeting.setProposer(proposer);
        meeting.setMeetingRoom(pubMeetingRoom);
        meeting.setMTopic(MTopic);
        meeting.setMStart(MStart);
        meeting.setMEnd(MEnd);
        meeting.setMDesc(MDesc);
        meeting.setMAttendee(MAttendee);
        meeting.setCurrentStep("审批中");

        Integer count = 0;
        meetingService.add(meeting, user);
        count++;

        return count.toString();
    }

    /**
     * 查找会议申请流程执行到的节点
     *
     * @param taskId
     * @return
     */
    @RequestMapping("getMeetingImpl")
    public ModelAndView getImg(@RequestParam(value = "taskId") String taskId) {
        ActivityImpl activityImpl = null;
        if (activitiUtil.getTask(taskId) != null) {
            activityImpl = activitiUtil.getActivityImplByTaskId(taskId);
        }

        ModelAndView mv = new ModelAndView();
        mv.setViewName("workflow/meetingFlowImg");
        mv.addObject("activity", activityImpl);
        return mv;
    }

    /**
     * 会议申请详情
     *
     * @param id
     * @return
     */
    @RequestMapping("meetingDetils")
    public ModelAndView meetingDetils(@RequestParam("id") Integer id) {
        ModelAndView mv = new ModelAndView();
        Meeting meeting = meetingService.findById(id);
        mv.setViewName("workflow/meetingDetils");
        mv.addObject("meeting", meeting);
        return mv;
    }

}
