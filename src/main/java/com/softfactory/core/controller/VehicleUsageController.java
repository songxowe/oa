package com.softfactory.core.controller;


import com.softfactory.core.service.VehicleUsageService;
import com.softfactory.core.util.*;
import com.softfactory.pojo.HrDept;
import com.softfactory.pojo.PubVehicle;
import com.softfactory.pojo.User;
import com.softfactory.pojo.VehicleUsage;
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
 * 车辆申请处理
 */
@Controller
public class VehicleUsageController {

    @Resource(name = "vehicleUsageService")
    private VehicleUsageService vehicleUsageService;
    @Resource(name = "activitiUtil")
    private ActivitiUtil activitiUtil;

    /**
     * 车辆申请列表
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @param beginDate
     * @param endDate
     * @param proposer
     * @return
     */
    @RequestMapping(value = "vehicleUsageList",produces = "application/json;charset=utf-8")
    public @ResponseBody String vehicleUsageList(@RequestParam(value = "page")Integer page,
                            @RequestParam(value = "rows")Integer rows,
                            @RequestParam(value = "sort")String sort,
                            @RequestParam(value = "order")String order,
                            @RequestParam(value = "beginDate",required = false)
                                   @DateTimeFormat(pattern = "yyyy-MM-dd") Date beginDate,
                            @RequestParam(value = "endDate",required = false)
                                   @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                            @RequestParam(value = "proposer",required = false)String proposer) {

        Integer pageNo = (page-1) * rows;
        Integer pageSize = rows;

        Pager<VehicleUsage> pager = vehicleUsageService.find(pageNo,pageSize,sort,order,proposer,beginDate,endDate);

        JsonConfig jc = new JsonConfig();
        jc.registerJsonValueProcessor(HrDept.class,new JsonDeptValueProcessor());
        jc.registerJsonValueProcessor(PubVehicle.class,new JsonPubVehicleValueProcessor());
        jc.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
        JSON json = JSONSerializer.toJSON(pager,jc);

        return json.toString();
    }

    /**
     * 跳转车辆申请页面
     * @return
     */
    @RequestMapping("/applyVehicle")
    public String applyVehicle() {
        return "workflow/applyVehicle";
    }

    /**
     * 车辆申请
     * @param proposer
     * @param deptId
     * @param VId
     * @param vuStart
     * @param vuEnd
     * @param vuReason
     * @param request
     * @return
     */
    @RequestMapping(value = "finishVehicleApply",produces = "application/json;charset=utf-8")
    public @ResponseBody String finishVehicleApply(@RequestParam(value = "proposer")String proposer,
                                     @RequestParam(value = "deptId")Integer deptId,
                                     @RequestParam(value = "VId")Integer VId,
                                     @RequestParam(value = "vuStart")
                                     @DateTimeFormat(pattern = "yyyy-MM-dd")Date vuStart,
                                     @RequestParam(value = "vuEnd")
                                     @DateTimeFormat(pattern = "yyyy-MM-dd")Date vuEnd,
                                     @RequestParam(value = "vuReason",required = false)String vuReason,
                                     HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Constants.USER_IN_SESSION);

        VehicleUsage vehicleUsage = new VehicleUsage();
        HrDept hrDept = new HrDept();
        PubVehicle pubVehicle = new PubVehicle();
        hrDept.setDeptId(deptId);
        pubVehicle.setVId(VId);
        vehicleUsage.setProposer(proposer);
        vehicleUsage.setHrDept(hrDept);
        vehicleUsage.setPubVehicle(pubVehicle);
        vehicleUsage.setVuStart(vuStart);
        vehicleUsage.setVuEnd(vuEnd);
        vehicleUsage.setVuReason(vuReason);
        vehicleUsage.setCurrentStep("审批中");
        Integer count = 0;
        vehicleUsageService.add(vehicleUsage,user);
        count++;
        return count.toString();
    }

    /**
     * 查找车辆申请流程执行到的节点
     * @param taskId
     * @return
     */
    @RequestMapping("getVehicleImpl")
    public ModelAndView getImg(@RequestParam(value = "taskId")String taskId) {
        ActivityImpl activityImpl = null;
        if(activitiUtil.getTask(taskId) != null) {
            activityImpl = activitiUtil.getActivityImplByTaskId(taskId);
        }

        ModelAndView mv = new ModelAndView();
        mv.setViewName("workflow/vehicleFlowImg");
        mv.addObject("activity",activityImpl);
        return mv;
    }

    /**
     * 车辆申请详细信息
     * @param id
     * @return
     */
    @RequestMapping("vehicleUsageDetils")
    public ModelAndView vehicleUsageDetils(@RequestParam(value = "id")Integer id) {
        ModelAndView mv = new ModelAndView();

        VehicleUsage vehicleUsage = vehicleUsageService.find(id);

        mv.setViewName("workflow/vehicleUsageDetils");
        mv.addObject("vehicleUsage",vehicleUsage);

        return mv;
    }
}
