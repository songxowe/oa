package com.softfactory.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 车辆信息实体
 * @author Administrator
 *
 */
public class PubVehicle implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Integer VId;
	private String VNum;		//车牌
	private String VType;		//车辆类型
	private String VDriver;		//司机
	private Double VPrice;		//价格
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date buyDate;		//购买日期
	private String VRemark;		//备注
	private String VStatus;		//车辆状态
	private Integer useingFalg = 0;	//使用状态


	public PubVehicle() {
	}


	public Integer getVId() {
		return this.VId;
	}

	public void setVId(Integer VId) {
		this.VId = VId;
	}

	public String getVNum() {
		return this.VNum;
	}

	public void setVNum(String VNum) {
		this.VNum = VNum;
	}

	public String getVType() {
		return this.VType;
	}

	public void setVType(String VType) {
		this.VType = VType;
	}

	public String getVDriver() {
		return this.VDriver;
	}

	public void setVDriver(String VDriver) {
		this.VDriver = VDriver;
	}

	public Double getVPrice() {
		return this.VPrice;
	}

	public void setVPrice(Double VPrice) {
		this.VPrice = VPrice;
	}

	public Date getBuyDate() {
		return this.buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public String getVRemark() {
		return this.VRemark;
	}

	public void setVRemark(String VRemark) {
		this.VRemark = VRemark;
	}

	public String getVStatus() {
		return this.VStatus;
	}

	public void setVStatus(String VStatus) {
		this.VStatus = VStatus;
	}

	public Integer getUseingFalg() {
		return this.useingFalg;
	}

	public void setUseingFalg(Integer useingFalg) {
		this.useingFalg = useingFalg;
	}

}