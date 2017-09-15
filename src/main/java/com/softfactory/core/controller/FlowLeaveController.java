package com.softfactory.core.controller;

import com.softfactory.core.service.FlowLeaveService;
import com.softfactory.core.service.LeaveBillService;
import com.softfactory.core.util.*;
import com.softfactory.pojo.FlowLeave;
import com.softfactory.pojo.LeaveBill;
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
 * 请假流程处理
 */
@Controller
public class FlowLeaveController {
    @Resource(name = "flowLeaveService")
    private FlowLeaveService flowLeaveService;
    @Resource(name = "activitiUtil")
    private ActivitiUtil activitiUtil;
    @Resource(name = "leaveBillService")
    private LeaveBillService leaveBillService;

    /**
     * 请假审批任务列表
     * @param page
     * @param rows
     * @param request
     * @return
     */
    @RequestMapping(value = "leaveTaskList",produces = "application/json;charset=utf-8")
    public @ResponseBody String taskList(@RequestParam(value = "page")Integer page,
                    @RequestParam(value = "rows")Integer rows,
                    HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute(Constants.USER_IN_SESSION);
        Integer pageNo = (page-1)*rows;
        Integer pageSize = page*rows;
        Pager<Task> pager = flowLeaveService.find(pageNo,pageSize,user.getUserTrueName(),"leaveProcess");
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
     * 请假审批介面
     * @param taskId
     * @return
     */
    @RequestMapping("leaveTaskUI")
    public ModelAndView taskUI(@RequestParam(value = "taskId")String taskId) {
        ModelAndView mv = new ModelAndView();

        String businessKey = activitiUtil.getBusinessKeyByTaskId(taskId);

        LeaveBill leaveBill = leaveBillService.findById(Integer.parseInt(businessKey));

        List<FlowLeave> flowLeaves = flowLeaveService.findByLeaveId(leaveBill.getLeaveId());

        mv.setViewName("workflow/leaveTaskForm");
        mv.addObject("leaveBill",leaveBill);
        mv.addObject("taskId",taskId);
        mv.addObject("flowLeaves",flowLeaves);
        return mv;
    }

    /**
     * 完成请假审批任务
     * @param checkIdea
     * @param taskId
     * @param checker
     * @return
     */
    @RequestMapping(value = "finishLeaveTask",produces = "application/json;charset=utf-8")
    public @ResponseBody String finishTask(@RequestParam(value = "checkIdea") String checkIdea,
                                           @RequestParam(value = "taskId") String taskId,
                                           @RequestParam(value = "checker") String checker) {
        Integer count = 0;
        flowLeaveService.completeTask(taskId,checker,checkIdea);
        count++;
        return count.toString();
    }

    /**
     * 获取未审批流程数量
     * @param request
     * @return
     */
    @RequestMapping(value = "getTaskCount",produces="application/json;charset=utf-8")
    public @ResponseBody String getTaskCount(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Constants.USER_IN_SESSION);
        Integer count = activitiUtil.getTaskCount(user.getUserTrueName(),"leaveProcess");
        count += activitiUtil.getTaskCount(user.getUserTrueName(),"meetingProcess");
        count += activitiUtil.getTaskCount(user.getUserTrueName(),"carProcess");

        return count.toString();
    }
}
