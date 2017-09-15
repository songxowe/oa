package com.softfactory.core.util;

import com.softfactory.core.service.UserService;
import com.softfactory.pojo.User;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工作流处理
 */
@Component("activitiUtil")
public class ActivitiUtil {
    @Resource(name="processEngine")
    private ProcessEngine processEngine;

    @Resource(name="userService")
    private UserService userService;

    /**
     * 查看流程图
     */
    public InputStream showImg(String pdid) {
        return this.processEngine
                .getRepositoryService()
                .getProcessDiagram(pdid);
    }

    /**
     * 启动流程实例
     *   1、启动流程实例的api
     *   第一个参数处理流程表id，第二个参数用户id，第三个参数流程部署key
     */
    public void startPI(Integer id,Integer userId,String key) {
        Map<String, Object> map = new HashMap<>();

        User user = userService.findById(userId);
        map.put("userId", user.getUserTrueName());
        User manager = userService.findById(user.getManagerId());
        map.put("manager", manager.getUserTrueName());

        this.processEngine.getRuntimeService()
                /**
                 * 第二个参数是businesskey:处理对应流程表的主键
                 */
                .startProcessInstanceByKey(key, ""+id, map);
    }

    /**
     * 当前登录人登录系统以后要执行的任务
     */
    public List<Task> getTasksByAssignee(Integer pageNo,Integer pageSize,String userId,String pdKey) {
        return this.processEngine
                .getTaskService()
                .createTaskQuery()
                .orderByDueDate()
                .taskAssignee(userId)
                .processDefinitionKey(pdKey)
                .desc()
                .listPage(pageNo,pageSize);
    }

    public Integer getTaskCount(String userId,String pdKey) {
        return this.processEngine
                .getTaskService()
                .createTaskQuery()
                .orderByDueDate()
                .taskAssignee(userId)
                .processDefinitionKey(pdKey)
                .desc()
                .list()
                .size();
    }

    public ActivityImpl getActivityImplByTaskId(String taskId) {
        Task task =  this.processEngine
                .getTaskService()
                .createTaskQuery()
                .taskId(taskId)
                .singleResult();
        ProcessInstance pi = this.processEngine
                .getRuntimeService()
                .createProcessInstanceQuery()
                .processInstanceId(task.getProcessInstanceId())
                .singleResult();

        ProcessDefinitionEntity processDefinitionEntity = this.getProcessDefinitionEntityByTaskId(taskId);

//        System.out.println(task);
//        System.out.println(pi);
//        System.out.println(processDefinitionEntity);

        return processDefinitionEntity.findActivity(pi.getActivityId());
    }

    /**
     * 根据taskId获取到ProcessDefinitionEntity
     */
    private ProcessDefinitionEntity getProcessDefinitionEntityByTaskId(String taskId) {
        Task task = this.processEngine
                .getTaskService()
                .createTaskQuery()
                .taskId(taskId)
                .singleResult();

        return (ProcessDefinitionEntity)this.processEngine
                .getRepositoryService()
                .getProcessDefinition(task.getProcessDefinitionId());
    }

    /**
     * 根据taskId查找businessKey
     */
    public String getBusinessKeyByTaskId(String taskId) {
        Task task = this.processEngine.getTaskService()
                .createTaskQuery()
                .taskId(taskId)
                .singleResult();
        ProcessInstance pi = this.processEngine.getRuntimeService()
                .createProcessInstanceQuery()
                .processInstanceId(task.getProcessInstanceId())
                .singleResult();
        return pi.getBusinessKey();
    }

    /**
     * 根据taskId完成任务，并且判断流程实例是否结束
     */
    public ProcessInstance finishTask(String taskId) {
        /**
         * 根据taskId提取任务
         */
        Task task = this.processEngine.getTaskService()
                .createTaskQuery()
                .taskId(taskId)
                .singleResult();
        //根据任务得到piid
        String piid = task.getProcessInstanceId();
        this.processEngine.getTaskService()
                .complete(taskId);
        //根据piid过滤流程实例
        ProcessInstance pi = this.processEngine.getRuntimeService()
                .createProcessInstanceQuery()
                .processInstanceId(piid)
                .singleResult();
        return pi;//如果整个流程实例结束，则pi为null，如果没有结束就是一个对象
    }

    public Task getTask(String taskId) {
        return this.processEngine.getTaskService()
                .createTaskQuery()
                .taskId(taskId)
                .singleResult();
    }
}
