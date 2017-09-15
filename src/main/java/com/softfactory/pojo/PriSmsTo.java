package com.softfactory.pojo;

import java.io.Serializable;
/**
 * 短信发送表
 * @author Administrator
 *
 */
public class PriSmsTo implements Serializable {


	private static final long serialVersionUID = 1L;
	private Integer smsToId; //短信发送序号
	private PriSms priSms; //短信序号
	private Integer toId; //收信人
	private Integer readFlag; //阅读状态
	private Integer deleteFlag; //删除状态


	public PriSmsTo() {
	}



	public Integer getSmsToId() {
		return this.smsToId;
	}

	public void setSmsToId(Integer smsToId) {
		this.smsToId = smsToId;
	}

	

	public PriSms getPriSms() {
		return priSms;
	}



	public void setPriSms(PriSms priSms) {
		this.priSms = priSms;
	}



	public Integer getToId() {
		return this.toId;
	}

	public void setToId(Integer toId) {
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