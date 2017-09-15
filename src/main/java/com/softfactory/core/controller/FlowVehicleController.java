package com.softfactory.core.controller;

import com.softfactory.core.service.FlowVehicleService;
import com.softfactory.core.service.VehicleUsageService;
import com.softfactory.core.util.*;
import com.softfactory.pojo.FlowVehicle;
import com.softfactory.pojo.User;
import com.softfactory.pojo.VehicleUsage;
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
 * 车辆申请流程controller
 */
@SuppressWarnings("all")
@Controller
public class FlowVehicleController {
    @Resource(name = "activitiUtil")
    private ActivitiUtil activitiUtil;
    @Resource(name = "flowVehicleService")
    private FlowVehicleService flowVehicleService;
    @Resource(name = "vehicleUsageService")
    private VehicleUsageService vehicleUsageService;

    /**
     * 车辆申请任务列表
     * @param page
     * @param rows
     * @param request
     * @return
     */
    @RequestMapping(value = "vehicleTaskList",produces = "application/json;charset=utf-8")
    public @ResponseBody String taskList(@RequestParam(value = "page")Integer page,
                    @RequestParam(value = "rows")Integer rows,
                    HttpServletRequest request) {

        User user = (User)request.getSession().getAttribute(Constants.USER_IN_SESSION);

        Integer pageNo = (page-1)*rows;
        Integer pageSize = page*rows;
        Pager<Task> pager = flowVehicleService.find(pageNo,pageSize,user.getUserTrueName(),"carProcess");

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
     * 车辆审批页面
     * @param taskId
     * @return
     */
    @RequestMapping("vehicleTaskUI")
    public ModelAndView taskUI(@RequestParam(value = "taskId")String taskId) {
        ModelAndView mv = new ModelAndView();

        String businessKey = activitiUtil.getBusinessKeyByTaskId(taskId);

        VehicleUsage vehicleUsage = vehicleUsageService.find(Integer.parseInt(businessKey));

        List<FlowVehicle> flowVehicles = flowVehicleService.find(vehicleUsage.getId());

        mv.setViewName("workflow/vehicleTaskForm");
        mv.addObject("vehicleUsage",vehicleUsage);
        mv.addObject("flowVehicles",flowVehicles);
        mv.addObject("taskId",taskId);
        return mv;
    }

    /**
     * 完成车辆审批任务
     * @param checkIdea
     * @param taskId
     * @param checker
     * @return
     */
    @RequestMapping(value = "finishVehicleTask",produces = "application/json;charset=utf-8")
    public @ResponseBody String finishTask(@RequestParam(value = "checkIdea") String checkIdea,
                                           @RequestParam(value = "taskId") String taskId,
                                           @RequestParam(value = "checker") String checker) {
        Integer count = 0;
        flowVehicleService.completeTask(taskId,checker,checkIdea);
        count++;
        return count.toString();
    }

    /**
     * 车辆申请审批记录
     * @param id
     * @return
     */
    @RequestMapping("getFlowVehicle")
    public ModelAndView getFlowVehicle(@RequestParam(value = "id")Integer id) {
        ModelAndView mv = new ModelAndView();

        List<FlowVehicle> flowVehicles = flowVehicleService.find(id);
        mv.setViewName("workflow/flowVehicle");
        mv.addObject("flowVehicles",flowVehicles);
        return mv;

    }
}
