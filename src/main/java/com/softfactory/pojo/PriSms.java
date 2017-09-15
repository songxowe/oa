package com.softfactory.pojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


/**
 * 短信表
 * @author Administrator
 *
 */
public class PriSms implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private Integer smsId; //短信id
	private Integer fromId; //发送人
	private String smsType; //短信类型
	private String content; //短信内容
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date sendTime; //发送时间
	private String remindUrl; //回复链接地址


	public PriSms() {
	}



	public Integer getSmsId() {
		return this.smsId;
	}

	public void setSmsId(Integer smsId) {
		this.smsId = smsId;
	}

	public Integer getFromId() {
		return this.fromId;
	}

	public void setFromId(Integer fromId) {
		this.fromId = fromId;
	}

	public String getSmsType() {
		return this.smsType;
	}

	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getRemindUrl() {
		return this.remindUrl;
	}

	public void setRemindUrl(String remindUrl) {
		this.remindUrl = remindUrl;
	}

}