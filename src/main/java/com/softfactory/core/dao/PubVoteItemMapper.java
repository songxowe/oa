package com.softfactory.core.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.softfactory.pojo.PubVoteItem;

@Repository("pubVoteItemMapper")
public interface PubVoteItemMapper {
    
	Integer add(PubVoteItem pubVoteItem);
	
	PubVoteItem findById(@Param("voteId") Integer voteId, @Param("userId") Integer userId);
}