package com.softfactory.pojo;


import java.util.Date;

/**
 * 会议申请表实体
 * @author Administrator
 *
 */
public class Meeting implements java.io.Serializable {


	private static final long serialVersionUID = 1L;

	private Integer id;
	private String proposer;	//申请人
	private Date applyDate = new Date();
	private PubMeetingRoom meetingRoom;		//会议室
	private String MTopic;		//会议主题
	private String MDesc;		//会议描述
	private String MAttendee;	//会议人员
	private Date MStart;
	private Date MEnd;
	private String currentStep;
	private String taskId;		//流程任务id

	public Meeting() {
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

	public PubMeetingRoom getMeetingRoom() {
		return meetingRoom;
	}

	public void setMeetingRoom(PubMeetingRoom meetingRoom) {
		this.meetingRoom = meetingRoom;
	}

	public String getMTopic() {
		return this.MTopic;
	}

	public void setMTopic(String MTopic) {
		this.MTopic = MTopic;
	}

	public String getMDesc() {
		return this.MDesc;
	}

	public void setMDesc(String MDesc) {
		this.MDesc = MDesc;
	}

	public String getMAttendee() {
		return this.MAttendee;
	}

	public void setMAttendee(String MAttendee) {
		this.MAttendee = MAttendee;
	}

	public Date getMStart() {
		return this.MStart;
	}

	public void setMStart(Date MStart) {
		this.MStart = MStart;
	}

	public Date getMEnd() {
		return this.MEnd;
	}

	public void setMEnd(Date MEnd) {
		this.MEnd = MEnd;
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