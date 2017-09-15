package com.softfactory.pojo;

import java.util.Date;

/**
 * 车辆申请表实体
 * @author Administrator
 *
 */
public class VehicleUsage implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Integer id;
	private String proposer;
	private Date applyDate = new Date();
	private HrDept hrDept;				//部门
	private PubVehicle pubVehicle;		//车辆
	private String vuReason;			//申请原因
	private Date vuStart;
	private Date vuEnd;
	private String currentStep;
	private String taskId;				//流程任务I


	public VehicleUsage() {
	}


	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public PubVehicle getPubVehicle() {
		return pubVehicle;
	}

	public void setPubVehicle(PubVehicle pubVehicle) {
		this.pubVehicle = pubVehicle;
	}

	public String getVuReason() {
		return this.vuReason;
	}

	public void setVuReason(String vuReason) {
		this.vuReason = vuReason;
	}

	public Date getVuStart() {
		return this.vuStart;
	}

	public void setVuStart(Date vuStart) {
		this.vuStart = vuStart;
	}

	public Date getVuEnd() {
		return this.vuEnd;
	}

	public void setVuEnd(Date vuEnd) {
		this.vuEnd = vuEnd;
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