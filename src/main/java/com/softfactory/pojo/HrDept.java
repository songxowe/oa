package com.softfactory.pojo;

import java.io.Serializable;

/**
 * 部门表实体
 * @author Administrator
 *
 */
public class HrDept implements Serializable {


	private static final long serialVersionUID = 1L;

	private Integer deptId;
	private String deptName;
	private String deptTel;
	private String deptFunc;

	public HrDept() {
	}

	public Integer getDeptId() {
		return this.deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptTel() {
		return this.deptTel;
	}

	public void setDeptTel(String deptTel) {
		this.deptTel = deptTel;
	}

	public String getDeptFunc() {
		return this.deptFunc;
	}

	public void setDeptFunc(String deptFunc) {
		this.deptFunc = deptFunc;
	}

}