package com.softfactory.core.service;

import java.util.List;

import com.softfactory.pojo.PubVoteItem;
import com.softfactory.pojo.PubVoteSub;

public interface PubVoteSubService {
	Integer insert(PubVoteSub record);
	PubVoteSub selectByPrimaryKey(Integer subId);
	Integer updateByPrimaryKeySelective(PubVoteSub record);

	Integer updateByPrimaryKey(PubVoteSub record, PubVoteItem pubVoteItem);
	List<PubVoteSub> findByVoteId(Integer voteId);
	
	//查找用户是否已经投票
	PubVoteItem findById(Integer voteId, Integer userId);
}
