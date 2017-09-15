package com.softfactory.pojo;

import java.util.Date;

/**
 * 请假单实体
 * @author Administrator
 *
 */
public class LeaveBill implements java.io.Serializable {


	private static final long serialVersionUID = 1L;

	private Integer leaveId;
	private String proposer;
	private Date applyDate = new Date();
	private HrDept hrDept;
	private String leaveType;
	private Date startDate;
	private Date endDate;
	private String leaveReason;
	private String currentStep;
	private String taskId;			//流程任务id


	public LeaveBill() {
	}



	public Integer getLeaveId() {
		return this.leaveId;
	}

	public void setLeaveId(Integer leaveId) {
		this.leaveId = leaveId;
	}

	public String getProposer() {
		return this.proposer;
	}

	public void setProposer(String proposer) {
		this.proposer = proposer;
	}

	public Date getApplyDate() {
		return this.applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public HrDept getHrDept() {
		return hrDept;
	}

	public void setHrDept(HrDept hrDept) {
		this.hrDept = hrDept;
	}

	public String getLeaveType() {
		return this.leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getLeaveReason() {
		return this.leaveReason;
	}

	public void setLeaveReason(String leaveReason) {
		this.leaveReason = leaveReason;
	}

	public String getCurrentStep() {
		return this.currentStep;
	}

	public void setCurrentStep(String currentStep) {
		this.currentStep = currentStep;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
}