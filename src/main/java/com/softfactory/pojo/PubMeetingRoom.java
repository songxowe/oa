package com.softfactory.pojo;

/**
 * 会议室实体
 * @author Administrator
 *
 */
public class PubMeetingRoom implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer mrId;
	private String mrName;			//会议室名字
	private Integer mrCapacity;		//可容纳人数
	private String mrDevice;		//设备情况
	private String mrDesc;			//描述
	private String mrPlace;			//地点
	private Integer useingFalg = 0;		//使用状态

	public PubMeetingRoom() {
	}

	public Integer getMrId() {
		return this.mrId;
	}

	public void setMrId(Integer mrId) {
		this.mrId = mrId;
	}

	public String getMrName() {
		return this.mrName;
	}

	public void setMrName(String mrName) {
		this.mrName = mrName;
	}

	public Integer getMrCapacity() {
		return this.mrCapacity;
	}

	public void setMrCapacity(Integer mrCapacity) {
		this.mrCapacity = mrCapacity;
	}

	public String getMrDevice() {
		return this.mrDevice;
	}

	public void setMrDevice(String mrDevice) {
		this.mrDevice = mrDevice;
	}

	public String getMrDesc() {
		return this.mrDesc;
	}

	public void setMrDesc(String mrDesc) {
		this.mrDesc = mrDesc;
	}

	public String getMrPlace() {
		return this.mrPlace;
	}

	public void setMrPlace(String mrPlace) {
		this.mrPlace = mrPlace;
	}

	public Integer getUseingFalg() {
		return this.useingFalg;
	}

	public void setUseingFalg(Integer useingFalg) {
		this.useingFalg = useingFalg;
	}

}