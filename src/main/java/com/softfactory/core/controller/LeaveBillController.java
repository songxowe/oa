package com.softfactory.core.controller;

import com.softfactory.core.service.FlowLeaveService;
import com.softfactory.core.service.LeaveBillService;
import com.softfactory.core.service.UserService;
import com.softfactory.core.util.*;
import com.softfactory.pojo.FlowLeave;
import com.softfactory.pojo.HrDept;
import com.softfactory.pojo.LeaveBill;
import com.softfactory.pojo.User;
import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.task.Task;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

@Controller
public class LeaveBillController {

    @Resource(name = "leaveBillService")
    private LeaveBillService leaveBillService;
    @Resource(name = "activitiUtil")
    private ActivitiUtil activitiUtil;
    @Resource(name = "userService")
    private UserService userService;
    @Resource(name = "flowLeaveService")
    private FlowLeaveService flowLeaveService;

    /**
     * 请假申请列表
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @param beginDate
     * @param endDate
     * @param proposer
     * @return
     */
    @RequestMapping(value = "leaveBills",produces = "application/json;charset=utf-8")
    public @ResponseBody String leaveBills(@RequestParam(value = "page")Integer page,
                      @RequestParam(value = "rows")Integer rows,
                      @RequestParam(value = "sort")String sort,
                      @RequestParam(value = "order")String order,
                      @RequestParam(value = "beginDate",required = false)
                             @DateTimeFormat(pattern = "yyyy-MM-dd") Date beginDate,
                      @RequestParam(value = "endDate",required = false)
                             @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                      @RequestParam(value = "proposer",required = false)String proposer) {
        System.out.println(page);
        Integer pageNo = (page-1)*rows;
        Integer pageSize = rows;

        Pager<LeaveBill> pager = leaveBillService.find(pageNo,pageSize,sort,order,beginDate,endDate,proposer);
        JsonConfig jc = new JsonConfig();
        jc.registerJsonValueProcessor(HrDept.class,new JsonDeptValueProcessor());
        jc.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
        JSON json = JSONSerializer.toJSON(pager,jc);
        return json.toString();

    }

    @RequestMapping("addLeave")
    public String addLeave() {
        return "workflow/addLeave";
    }

    /**
     * 申请请假
     */
    @RequestMapping(value = "applyLeave",produces = "application/json;charset=utf-8")
    public @ResponseBody String startLeaveProcess(@RequestParam(value = "proposer")String proposer,
                                                  @RequestParam(value = "leaveType")String leaveType,
                                                  @RequestParam(value = "deptId")Integer deptId,
                                                  @RequestParam(value = "startDate")
                                                      @DateTimeFormat(pattern = "yyyy-MM-dd")Date startDate,
                                                  @RequestParam(value = "endDate")
                                                      @DateTimeFormat(pattern = "yyyy-MM-dd")Date endDate,
                                                  @RequestParam(value = "leaveReason")String leaveReason,
                                                  HttpServletRequest request) {


        System.out.println(endDate+".........................");

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(Constants.USER_IN_SESSION);
        LeaveBill leaveBill = new LeaveBill();
        HrDept hrDept = new HrDept();
        hrDept.setDeptId(deptId);
        leaveBill.setProposer(proposer);
        leaveBill.setHrDept(hrDept);
        leaveBill.setLeaveType(leaveType);
        leaveBill.setStartDate(startDate);
        leaveBill.setEndDate(endDate);
        leaveBill.setLeaveReason(leaveReason);
        leaveBill.setCurrentStep("审批中");

        Integer count = 0;

        leaveBillService.add(leaveBill,user);

        count++;

        return count.toString();
    }

    /**
     * 查找流程执行到的节点
     * @param taskId
     * @return
     */
    @RequestMapping("getImg")
    public ModelAndView getImg(@RequestParam(value = "taskId")String taskId) {
        ActivityImpl activityImpl = null;
        if(activitiUtil.getTask(taskId) != null) {
            activityImpl = activitiUtil.getActivityImplByTaskId(taskId);
        }

        ModelAndView mv = new ModelAndView();
        mv.setViewName("workflow/leaveFlowImg");
        mv.addObject("activity",activityImpl);
        return mv;
    }

    /**
     * 查看流程图
     * @param pdid
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("flowImg")
    public String flowImg(@RequestParam(value = "pdid")String pdid, HttpServletResponse response) throws Exception {
        InputStream in = activitiUtil.showImg(pdid);
        OutputStream out = response.getOutputStream();
        for(int b=-1;(b=in.read())!=-1;) {
            out.write(b);
        }
        return null;
    }

    /**
     * 查看审批记录
     * @param leaveId
     * @return
     */
    @RequestMapping("getFlowLeave")
    public ModelAndView getFlowLeave(@RequestParam(value = "leaveId")Integer leaveId) {
        List<FlowLeave> flowLeaves = flowLeaveService.findByLeaveId(leaveId);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("workflow/flowLeave");
        mv.addObject("flowLeaves",flowLeaves);
        return mv;
    }

}
