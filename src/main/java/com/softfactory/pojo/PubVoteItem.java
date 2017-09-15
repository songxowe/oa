package com.softfactory.pojo;

import java.io.Serializable;

public class PubVoteItem implements Serializable {
    
	private static final long serialVersionUID = 1L;

	private Integer itemId;

    private Integer subId;

    private Integer userId;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getSubId() {
        return subId;
    }

    public void setSubId(Integer subId) {
        this.subId = subId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

	@Override
	public String toString() {
		return "PubVoteItem [itemId=" + itemId + ", subId=" + subId + ", userId=" + userId + "]";
	}
}