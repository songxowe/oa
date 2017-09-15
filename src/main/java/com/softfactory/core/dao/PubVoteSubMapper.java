package com.softfactory.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.softfactory.pojo.PubVoteSub;



@Repository("pubVoteSubMapper")
public interface PubVoteSubMapper {
    int deleteByPrimaryKey(Integer subId);

    int insert(PubVoteSub record);

    int insertSelective(PubVoteSub record);

    PubVoteSub selectByPrimaryKey(Integer subId);

    int updateByPrimaryKeySelective(PubVoteSub record);

    int updateByPrimaryKey(PubVoteSub record);
    
    List<PubVoteSub> findByVoteId(Integer voteId);
}