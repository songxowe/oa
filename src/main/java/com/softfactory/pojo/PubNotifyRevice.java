package com.softfactory.pojo;

import java.io.Serializable;
/**
 * 公告接收表
 * @author Administrator
 *
 */
public class PubNotifyRevice implements Serializable {


	private static final long serialVersionUID = 1L;
	private Integer reviceId; //公告接收序号
	//private Integer notifyId; //公告序号
	private Integer toId; //接收人
	private Integer readFlag; //阅读状态
	private Integer deleteFlag; //删除状态
	private PubNotify pubNotify;
	

	public PubNotify getPubNotify() {
		return pubNotify;
	}


	public void setPubNotify(PubNotify pubNotify) {
		this.pubNotify = pubNotify;
	}


	public PubNotifyRevice() {
	}


	public Integer getReviceId() {
		return this.reviceId;
	}

	public void setReviceId(Integer reviceId) {
		this.reviceId = reviceId;
	}

/*	public Integer getNotifyId() {
		return this.notifyId;
	}

	public void setNotifyId(Integer notifyId) {
		this.notifyId = notifyId;
	}*/

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