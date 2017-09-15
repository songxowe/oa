package com.softfactory.pojo;

import java.util.Date;

/**
 * 请假审批处理实体
 * @author Administrator
 *
 */
public class FlowLeave implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String checker;
	private Date checkDate = new Date();
	private String checkIdea;
	private String checkStatus;
	private Integer leaveId;


	public FlowLeave() {
	}

	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getChecker() {
		return this.checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	public Date getCheckDate() {
		return this.checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public String getCheckIdea() {
		return this.checkIdea;
	}

	public void setCheckIdea(String checkIdea) {
		this.checkIdea = checkIdea;
	}

	public String getCheckStatus() {
		return this.checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	public Integer getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(Integer leaveId) {
		this.leaveId = leaveId;
	}
}