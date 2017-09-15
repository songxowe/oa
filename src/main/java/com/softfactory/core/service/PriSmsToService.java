package com.softfactory.core.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.softfactory.core.dao.PriSmsToMapper;
import com.softfactory.core.util.Pager;
import com.softfactory.pojo.PriSmsTo;
import com.softfactory.pojo.PubNotifyRevice;
@Service("priSmsToService")
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class PriSmsToService {
	@Resource(name="priSmsToMapper")
	private PriSmsToMapper priSmsToMapper;
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int add(PriSmsTo priSmsTo){
		return priSmsToMapper.add(priSmsTo);
	}
	
	public Pager<PriSmsTo> find(Integer page, Integer rows, String sort, String order, String smsType, Date beginDate, Date endDate,Integer toId,Integer readFlag,Integer fromId) {
	    Pager<PriSmsTo> pager = new Pager<>();
	    pager.setRows(priSmsToMapper.findPager(page, rows, sort, order, smsType, beginDate, endDate, toId,readFlag,fromId));
	    pager.setTotal(priSmsToMapper.findPagerTotal(smsType, beginDate, endDate,toId,readFlag,fromId));
	    return pager;
	  }
	
	public int findByReadFlag(Integer toId){
		return priSmsToMapper.findByReadFlag(toId);
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void modifyReadFlag(Integer smsToId) {
		priSmsToMapper.modifyReadFlag(smsToId);
		
	}

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public int removePriSms(Integer smsId, Integer toId) {
		
		return priSmsToMapper.removePriSms(smsId, toId);
	}
	
	/*
	 * 页面实时刷新
	 */
	public PriSmsTo findBySendTime(Date newDate,Integer toId){
		return priSmsToMapper.findBySendTime(newDate,toId);
	}

}
