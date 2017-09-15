package com.softfactory.pojo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
/**
 * 公告表
 * @author Administrator
 *
 */
public class PubNotify implements Serializable{
	

	private static final long serialVersionUID = 1L;
	private Integer notifyId;  //公告序号
	private String subject; //标题
	private String content; //内容
	private Integer fromId; //发布人
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTime; //创建时间
	private String toDept; //发布范围(部门)
	private String toRank; //发布范围(职位)
	private String toId; //发布范围(个人)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate; //生效日期
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate; //终止日期
	private String attachment; //附件
	private Integer isTop; //是否置顶
	private String notifyStatus; //公告通知状态

	public PubNotify() {
	}

	public Integer getNotifyId() {
		return notifyId;
	}
	public void setNotifyId(Integer notifyId) {
		this.notifyId = notifyId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getFromId() {
		return fromId;
	}
	public void setFromId(Integer fromId) {
		this.fromId = fromId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getToDept() {
		return toDept;
	}
	public void setToDept(String toDept) {
		this.toDept = toDept;
	}
	public String getToRank() {
		return toRank;
	}
	public void setToRank(String toRank) {
		this.toRank = toRank;
	}
	public String getToId() {
		return toId;
	}
	public void setToId(String toId) {
		this.toId = toId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public Integer getIsTop() {
		return isTop;
	}
	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}
	public String getNotifyStatus() {
		return notifyStatus;
	}
	public void setNotifyStatus(String notifyStatus) {
		this.notifyStatus = notifyStatus;
	}

}
