package com.softfactory.core.util;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class ManagerTask implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        // TODO Auto-generated method stub
        String manager = (String)delegateTask.getVariable("manager");
        delegateTask.setAssignee(manager);
    }
}
