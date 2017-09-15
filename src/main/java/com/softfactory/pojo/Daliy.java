package com.softfactory.pojo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Daliy implements Serializable {

	private static final long serialVersionUID = 1L;
	private  Integer id;
	private String username;
	private String priopity;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date logDate;
	private String classmate;
	private String method;
	private String msg;
	public Daliy() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPriopity() {
		return priopity;
	}
	public void setPriopity(String priopity) {
		this.priopity = priopity;
	}
	
	
	public Date getLogDate() {
		return logDate;
	}
	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}
	public String getClassmate() {
		return classmate;
	}
	public void setClassmate(String classmate) {
		this.classmate = classmate;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "Daliy [id=" + id + ", username=" + username + ", priopity=" + priopity + ", logdate=" + logDate
				+ ", classmate=" + classmate + ", method=" + method + ", msg=" + msg + "]";
	}


}
