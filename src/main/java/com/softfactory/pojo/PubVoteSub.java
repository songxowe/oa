package com.softfactory.pojo;

import java.io.Serializable;

public class PubVoteSub implements Serializable {
    
	private static final long serialVersionUID = 1L;

	private Integer subId;

    private Integer voteId;

    private String title;

    private String descn;

    private Integer voteCount;

    public Integer getSubId() {
        return subId;
    }

    public void setSubId(Integer subId) {
        this.subId = subId;
    }

    public Integer getVoteId() {
        return voteId;
    }

    public void setVoteId(Integer voteId) {
        this.voteId = voteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDescn() {
        return descn;
    }

    public void setDescn(String descn) {
        this.descn = descn == null ? null : descn.trim();
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

	@Override
	public String toString() {
		return "PubVoteSub [subId=" + subId + ", voteId=" + voteId + ", title=" + title + ", descn=" + descn
				+ ", voteCount=" + voteCount + "]";
	}
}