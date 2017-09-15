package com.softfactory.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


/**
 * PriMail entity. @author MyEclipse Persistence Tools
 */

public class PriMail  implements java.io.Serializable {


    // Fields    

     private Integer mailId;
     private String fromId;
     private String toId;
     private String copyTo;
     private String secreatTo;
     private String subject;
     private String content;
 	@DateTimeFormat(pattern="yyyy-MM-dd")
     private Date sendTime;
     private String attachment;
     private String sendFlag;
     private String smsRemind;
     private String important;
     private String mailSize;

     
    // private User user;
    // Constructors

    /** default constructor */
    public PriMail() {
    }

    
    

    public Integer getMailId() {
        return this.mailId;
    }
    
    public void setMailId(Integer mailId) {
        this.mailId = mailId;
    }

    public String getFromId() {
        return this.fromId;
    }
    
    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return this.toId;
    }
    
    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getCopyTo() {
        return this.copyTo;
    }
    
    public void setCopyTo(String copyTo) {
        this.copyTo = copyTo;
    }

    public String getSecreatTo() {
        return this.secreatTo;
    }
    
    public void setSecreatTo(String secreatTo) {
        this.secreatTo = secreatTo;
    }

    public String getSubject() {
        return this.subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
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


    public String getAttachment() {
        return this.attachment;
    }
    
    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getSendFlag() {
        return this.sendFlag;
    }
    
    public void setSendFlag(String sendFlag) {
        this.sendFlag = sendFlag;
    }

    public String getSmsRemind() {
        return this.smsRemind;
    }
    
    public void setSmsRemind(String smsRemind) {
        this.smsRemind = smsRemind;
    }

    public String getImportant() {
        return this.important;
    }
    
    public void setImportant(String important) {
        this.important = important;
    }

    public String getMailSize() {
        return this.mailSize;
    }
    
    public void setMailSize(String mailSize) {
        this.mailSize = mailSize;
    }




	/*public User getUser() {
		return user;
	}




	public void setUser(User user) {
		this.user = user;
	}

*/










}