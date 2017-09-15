package com.softfactory.core.service;


import java.util.Date;
import java.util.List;

import com.softfactory.core.util.Pager;
import com.softfactory.pojo.PubVote;


public interface PubVoteService {
	Integer insert(PubVote record);
	PubVote selectByPrimaryKey(Integer voteId);
	Integer updateByPrimaryKey(PubVote record);
	
	//返回投票信息的ID
	Integer add(PubVote pubVote, String ids[]);
	
	Pager<PubVote> findPage(Integer page, Integer rows, String sort, String order, String voteStatus, String subject, Date beginDate, Date endDate);
	
	List<PubVote> findPageBefore(Integer page, Integer rows, String sort, String order, String voteStatus, String subject, Date beginDate, Date endDate);

}
