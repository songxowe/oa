package com.softfactory.core.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.softfactory.core.dao.PubVoteItemMapper;
import com.softfactory.core.dao.PubVoteSubMapper;
import com.softfactory.pojo.PubVoteItem;
import com.softfactory.pojo.PubVoteSub;


@Service("pubVoteSubService")
@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
public class PubVoteSubServiceImpl implements PubVoteSubService {
	
	@Resource(name="pubVoteSubMapper")
	private PubVoteSubMapper pubVoteSubMapper;
	
	@Resource(name="pubVoteItemMapper")
	private PubVoteItemMapper pubVoteItemMapper;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public Integer insert(PubVoteSub record) {
		// TODO Auto-generated method stub
		return pubVoteSubMapper.insert(record);
	}

	@Override
	public List<PubVoteSub> findByVoteId(Integer voteId) {
		// TODO Auto-generated method stub
		return pubVoteSubMapper.findByVoteId(voteId);
	}

	@Override
	public PubVoteSub selectByPrimaryKey(Integer subId) {
		// TODO Auto-generated method stub
		return pubVoteSubMapper.selectByPrimaryKey(subId);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public Integer updateByPrimaryKeySelective(PubVoteSub record) {
		// TODO Auto-generated method stub
		return pubVoteSubMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 投票
	 * @param record
	 * @param pubVoteItem
	 * @return
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public Integer updateByPrimaryKey(PubVoteSub record,PubVoteItem pubVoteItem) {
		// TODO Auto-generated method stub
		Integer count=pubVoteSubMapper.updateByPrimaryKey(record);
		count +=pubVoteItemMapper.add(pubVoteItem);
		return count;
	}

	@Override
	public PubVoteItem findById(Integer voteId, Integer userId) {
		// TODO Auto-generated method stub
		return pubVoteItemMapper.findById(voteId, userId);
	}
}
