package com.softfactory.core.controller;

import com.softfactory.core.service.FlowMeetingService;
import com.softfactory.core.service.MeetingService;
import com.softfactory.core.util.*;
import com.softfactory.pojo.FlowMeeting;
import com.softfactory.pojo.Meeting;
import com.softfactory.pojo.User;
import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 会议申请流程处理
 */
@SuppressWarnings("all")
@Controller
public class FlowMeetingController {
    @Resource(name = "activitiUtil")
    private ActivitiUtil activitiUtil;
    @Resource(name = "flowMeetingService")
    private FlowMeetingService flowMeetingService;
    @Resource(name = "meetingService")
    private MeetingService meetingService;


    /**
     * 会议审批任务列表
     * @param page
     * @param rows
     * @param request
     * @return
     */
    @RequestMapping(value = "meetingTaskList",produces = "application/json;charset=utf-8")
    public @ResponseBody String taskList(@RequestParam(value = "page")Integer page,
                    @RequestParam(value = "rows")Integer rows,
                    HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute(Constants.USER_IN_SESSION);
        Integer pageNo = (page-1)*rows;
        Integer pageSize = page*rows;
        Pager<Task> pager = flowMeetingService.find(pageNo,pageSize,user.getUserTrueName(),"meetingProcess");

        Pager<TaskUtil> tasks = new Pager<>();
        tasks.setTotal(pager.getTotal());
        List<TaskUtil> list = new ArrayList<>();
        for(Task task : pager.getRows()) {
            TaskUtil t = new TaskUtil();
            t.setId(task.getId());
            t.setName(task.getName());
            t.setCreateTime(task.getCreateTime());
            t.setAssignee(task.getAssignee());
            list.add(t);
        }
        tasks.setRows(list);

        JsonConfig jc = new JsonConfig();
        jc.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor("yyyy-MM-dd HH:mm:ss"));
        JSON json = JSONSerializer.toJSON(tasks,jc);
        System.out.println(json.toString());
        return json.toString();
    }

    /**
     * 会议审批介面
     * @param taskId
     * @return
     */
    @RequestMapping("meetingTaskUI")
    public ModelAndView taskUI(@RequestParam(value = "taskId")String taskId) {
        ModelAndView mv = new ModelAndView();

        String businessKey = activitiUtil.getBusinessKeyByTaskId(taskId);

        Meeting meeting = meetingService.findById(Integer.parseInt(businessKey));

        List<FlowMeeting> flowMeetings = flowMeetingService.find(meeting.getId());

        mv.setViewName("workflow/meetingTaskForm");
        mv.addObject("taskId",taskId);
        mv.addObject("flowMeetings",flowMeetings);
        mv.addObject("meeting",meeting);
        return mv;
    }

    /**
     * 完成会议审批任务
     * @param checkIdea
     * @param taskId
     * @param checker
     * @return
     */
    @RequestMapping(value = "finishMeetingTask",produces = "application/json;charset=utf-8")
    public @ResponseBody String finishTask(@RequestParam(value = "checkIdea") String checkIdea,
                                           @RequestParam(value = "taskId") String taskId,
                                           @RequestParam(value = "checker") String checker) {
        Integer count = 0;
        flowMeetingService.completeTask(taskId,checker,checkIdea);
        count++;
        return count.toString();
    }

    /**
     * 审批记录
     * @param id
     * @return
     */
    @RequestMapping("getFlowMeeting")
    public ModelAndView getFlowLeave(@RequestParam(value = "id")Integer id) {
        List<FlowMeeting> flowMeetings = flowMeetingService.find(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("workflow/flowMeeting");
        mv.addObject("flowMeetings",flowMeetings);
        return mv;
    }

}
