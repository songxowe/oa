package com.softfactory.core.util;

import java.util.Date;

/**
 * 流程任务工具类（转JSON用）
 */
public class TaskUtil {
    private String id;          //任务ID
    private String name;        //任务名称
    private Date createTime;    //申请时间
    private String assignee;    //办理人

    public TaskUtil() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }
}
