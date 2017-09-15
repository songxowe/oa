package com.softfactory.core.test;

import com.softfactory.core.service.*;
import com.softfactory.core.util.Pager;
import com.softfactory.pojo.*;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.task.Task;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

public class ServiceTest {

    private HrDeptService hrDeptService;
    private LeaveBillService leaveBillService;
    private FlowLeaveService flowLeaveService;
    private ProcessEngine processEngine;
    private MeetingService meetingService;
    private PubMeetingRoomService pubMeetingRoomService;
    private PubVehicleService pubVehicleService;
    private VehicleUsageService vehicleUsageService;
    private HrEmpService hrEmpService;
    private PubNotifyService pubNotifyService;

    @SuppressWarnings("resource")
    @Before
    public void init() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        hrDeptService = ctx.getBean("hrDeptService", HrDeptService.class);
        leaveBillService = ctx.getBean("leaveBillService",LeaveBillService.class);
        processEngine = ctx.getBean("processEngine",ProcessEngine.class);
        flowLeaveService = ctx.getBean("flowLeaveService",FlowLeaveService.class);
        meetingService = ctx.getBean("meetingService",MeetingService.class);
        pubMeetingRoomService = ctx.getBean("pubMeetingRoomService",PubMeetingRoomService.class);
        pubVehicleService = ctx.getBean("pubVehicleService",PubVehicleService.class);
        vehicleUsageService = ctx.getBean("vehicleUsageService",VehicleUsageService.class);
        hrEmpService = ctx.getBean("hrEmpService",HrEmpService.class);
        pubNotifyService = ctx.getBean("pubNotifyService",PubNotifyService.class);
    }

    @Test
    public void testFind() {
        List<HrDept> list = hrDeptService.find();
        System.out.println(list.size());
    }

    @Test
    public void test1() {
        Integer pageNO = 0;
        Integer pageSize = 3;
        String sort = "leaveId";
        String order = "asc";
        Date beginDate = null;
        Date endDate = null;
        String proposer = null;

        Pager<LeaveBill> pager = leaveBillService.find(pageNO,pageSize,sort,order,beginDate,endDate,proposer);
        System.out.println(pager.getTotal());
    }

    @Test
    public void test2() {
        LeaveBill leaveBill = new LeaveBill();
        leaveBill.setProposer("曾凌");
        //System.out.println(leaveBillService.add(leaveBill));
        System.out.println(leaveBillService.getLastId());
    }

    /**
     * 部署请假流程
     */
    @Test
    public void testDeploy1() {
        processEngine.getRepositoryService().createDeployment().name("请假流程").addClasspathResource("leaveProcess.bpmn")
                .addClasspathResource("leaveProcess.png").deploy();
    }

    /**
     * 部署会议申请流程
     */
    @Test
    public void testDeploy2() {
        processEngine.getRepositoryService().createDeployment().name("会议申请流程").addClasspathResource("meetingProcess.bpmn")
                .addClasspathResource("meetingProcess.png").deploy();
    }

    /**
     * 部署车辆申请流程
     */
    @Test
    public void testDeploy3() {
        processEngine.getRepositoryService().createDeployment().name("车辆申请流程").addClasspathResource("carProcess.bpmn")
                .addClasspathResource("carProcess.png").deploy();
    }

    @Test
    public void test3() {
        Integer pageNo = 0;
        Integer pageSize = 3;
        String userId = "曾凌";
        Pager<Task> pager = flowLeaveService.find(pageNo,pageSize,userId,"leaveProcess");
        System.out.println(pager.getTotal());
    }

    @Test
    public void test4() {
        Integer pageNo = 0;
        Integer pageSize = 3;
        String sort = "id";
        String order = "asc";
        String proposer = null;
        String MTopic = null;
        Date beginDate = null;
        Date endDate = null;
        Pager<Meeting> pager = meetingService.find(pageNo,pageSize,sort,order,proposer,MTopic,beginDate,endDate);
        System.out.println(pager.getTotal());

    }

    @Test
    public void test5() {
        List<PubMeetingRoom> list = pubMeetingRoomService.find();
        System.out.println(list.size());
    }

    @Test
    public void test6() {
        List<Task> tasks =
        processEngine
                .getTaskService()
                .createTaskQuery()
                .orderByDueDate()
                .taskAssignee("曾凌")
                .processDefinitionKey("leaveProcess")
                .desc()
                .list();
        System.out.println(tasks.size());
    }

    @Test
    public void test7() {
        List<PubVehicle> list = pubVehicleService.find();
        System.out.println(list.size());
    }

    @Test
    public void test8() {
        Integer pageNo = 0;
        Integer pageSize = 2;
        String sort = "id";
        String order = "asc";
        String proposer = null;
        Date beginDate = null;
        Date endDate = null;
        Pager<VehicleUsage> pager = vehicleUsageService.find(pageNo,pageSize,sort,order,proposer,beginDate,endDate);
        for (VehicleUsage vehicleUsage : pager.getRows()) {
            System.out.println(vehicleUsage.getHrDept().getDeptName()+" "+vehicleUsage.getPubVehicle().getVNum());
        }
    }

    @Test
    public void test9() {
        HrEmp hrEmp = new HrEmp();
        HrDept hrDept = new HrDept();
        hrDept.setDeptId(1);
        hrEmp.setEmpName("李明");
        hrEmp.setWorkId("A0001");
        hrEmp.setHrDept(hrDept);
        if(hrEmpService.add(hrEmp) > 0) {
            System.out.println("ok");
        }
    }

    @Test
    public void test10() {
        Integer pageNo = 0;
        Integer pageSize = 3;
        String sort = "empId";
        String order = "desc";
        String empName = null;
        String workId = null;
        Integer deptId = 0;
        Date beginDate = null;
        Date endDate = null;
        Integer status = 1;
        Pager<HrEmp> pager = hrEmpService.find(pageNo,pageSize,sort,order,empName,workId,deptId,beginDate,endDate,status);
        System.out.println(pager.getRows().size());
    }

    @Test
    public void test11() {
        HrEmp hrEmp = hrEmpService.find(1);
        hrEmp.setAddress("长沙");
        if(hrEmpService.modify(hrEmp)>0) {
            System.out.println("ok");
        }
    }

    @Test
    public void test12() {
        Integer pageNo = 0;
        Integer pageSize = 4;
        String sort = "VId";
        String order = "asc";
        String VNum = null;
        Date beginDate = null;
        Date endDate = null;
        /*Pager<PubMeetingRoom> pager = pubMeetingRoomService.find(pageNo,pageSize,sort,order,mrName);
        for(PubMeetingRoom pubMeetingRoom : pager.getRows()) {
            System.out.println(pubMeetingRoom.getMrName());
        }*/
        Pager<PubVehicle> pager = pubVehicleService.find(pageNo,pageSize,sort,order,VNum,beginDate,endDate);
        System.out.println(pager.getRows().size());
    }

    @Test
    public void test13() {
        PubNotify pubNotify = new PubNotify();
        pubNotify.setSubject("dfdfd");
        if(pubNotifyService.add(pubNotify)>0) {
            System.out.println("ok");
        }
      /*  Integer pageNo = 0;
        Integer pageSize = 4;
        String sort = "notifyId";
        String order = "asc";
        String subject = null;
        Date beginDate = null;
        Date endDate = null;
        List<PubNotify> list = pubNotifyService.find();
        Pager<PubNotify> pager = pubNotifyService.find(pageNo,pageSize,sort,order,subject,beginDate,endDate);
        System.out.println(pager.getRows().size());
        System.out.println(list.size());*/
    }
}
