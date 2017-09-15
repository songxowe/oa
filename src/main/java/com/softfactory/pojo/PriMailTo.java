package com.softfactory.pojo;

/**
 * PriMailTo entity. @author MyEclipse Persistence Tools
 */

public class PriMailTo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer mailToId;
	private PriMail priMail;
	private String toId;
	private Integer readFlag;
	private Integer deleteFlag;
	
	
	/** default constructor */
	public PriMailTo() {
	}

	

	public Integer getMailToId() {
		return this.mailToId;
	}

	public void setMailToId(Integer mailToId) {
		this.mailToId = mailToId;
	}



	public PriMail getPriMail() {
		return priMail;
	}



	public void setPriMail(PriMail priMail) {
		this.priMail = priMail;
	}



	public String getToId() {
		return this.toId;
	}

	public void setToId(String toId) {
		this.toId = toId;
	}

	public Integer getReadFlag() {
		return this.readFlag;
	}

	public void setReadFlag(Integer readFlag) {
		this.readFlag = readFlag;
	}

	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

}