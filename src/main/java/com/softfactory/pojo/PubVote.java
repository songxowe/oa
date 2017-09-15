package com.softfactory.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class PubVote implements Serializable {
    
	private static final long serialVersionUID = 1L;

	private Integer voteId;

    private String subject;

    private String descn;

    private Integer fromId;
    
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    private String toDept;

    private String toRank;

    private String toId;

    private String canView;

    private String voteStatus;
    
    private User user;
    
    private List<PubVoteSub> subs;
    
    public Integer getVoteId() {
        return voteId;
    }

    public void setVoteId(Integer voteId) {
        this.voteId = voteId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public String getDescn() {
        return descn;
    }

    public void setDescn(String descn) {
        this.descn = descn == null ? null : descn.trim();
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

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getToDept() {
        return toDept;
    }

    public void setToDept(String toDept) {
        this.toDept = toDept == null ? null : toDept.trim();
    }

    public String getToRank() {
        return toRank;
    }

    public void setToRank(String toRank) {
        this.toRank = toRank == null ? null : toRank.trim();
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId == null ? null : toId.trim();
    }

    public String getCanView() {
        return canView;
    }

    public void setCanView(String canView) {
        this.canView = canView == null ? null : canView.trim();
    }

    public String getVoteStatus() {
        return voteStatus;
    }

    public void setVoteStatus(String voteStatus) {
        this.voteStatus = voteStatus == null ? null : voteStatus.trim();
    }

	public List<PubVoteSub> getSubs() {
		return subs;
	}

	public void setSubs(List<PubVoteSub> subs) {
		this.subs = subs;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "PubVote [voteId=" + voteId + ", subject=" + subject + ", descn=" + descn + ", fromId=" + fromId
				+ ", createTime=" + createTime + ", endTime=" + endTime + ", toDept=" + toDept + ", toRank=" + toRank
				+ ", toId=" + toId + ", canView=" + canView + ", voteStatus=" + voteStatus + "]";
	}
}