package com.softfactory.pojo;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 档案表实体
 * @author Administrator
 *
 */
public class HrEmp implements Serializable {


	private static final long serialVersionUID = 1L;


	private Integer empId;
	private String empName;
	private String workId;		//工号
	private HrDept hrDept;		//部门
	private Integer gender;		//性别
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	private String nation;		//民族
	private String idCard;		//身份证
	private String photo;
	private String isMarry;		//婚姻状况
	private String politics;	//政治面貌
	private String diploma;		//学历
	private String title;		//职称
	private String university;	//毕业院校
	private String speciality;	//专业
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date joinDate;		//入职时间
	private String rank;		//职务
	private String telephone;	//家庭电话
	private String cellphone;	//手机
	private String email;
	private String address;
	private String remark;
	private Integer status = 1;

	public HrEmp() {
	}

	public Integer getEmpId() {
		return this.empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return this.empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getWorkId() {
		return this.workId;
	}

	public void setWorkId(String workId) {
		this.workId = workId;
	}

	public HrDept getHrDept() {
		return hrDept;
	}

	public void setHrDept(HrDept hrDept) {
		this.hrDept = hrDept;
	}

	public Integer getGender() {
		return this.gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getNation() {
		return this.nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getIdCard() {
		return this.idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getIsMarry() {
		return this.isMarry;
	}

	public void setIsMarry(String isMarry) {
		this.isMarry = isMarry;
	}

	public String getPolitics() {
		return this.politics;
	}

	public void setPolitics(String politics) {
		this.politics = politics;
	}

	public String getDiploma() {
		return this.diploma;
	}

	public void setDiploma(String diploma) {
		this.diploma = diploma;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUniversity() {
		return this.university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getSpeciality() {
		return this.speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public Date getJoinDate() {
		return this.joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getRank() {
		return this.rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCellphone() {
		return this.cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}