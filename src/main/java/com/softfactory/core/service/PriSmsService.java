package com.softfactory.core.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.softfactory.core.dao.PriSmsMapper;
import com.softfactory.core.dao.PriSmsToMapper;
import com.softfactory.pojo.PriSms;
import com.softfactory.pojo.PriSmsTo;
@Service("priSmsService")
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class PriSmsService {
	@Resource(name="priSmsMapper")
	private PriSmsMapper priSmsMapper;
	@Resource(name="priSmsToMapper")
	private PriSmsToMapper priSmsToMapper;
	/*
	 * 系统添加消息
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int add(PriSms priSms){
		return priSmsMapper.add(priSms);
	}
	
	/*
	 * 自行发送消息
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int addByUser(PriSms priSms,int[] ids){
		priSmsMapper.add(priSms);
		Integer lastId = priSmsMapper.lastId();
		priSms.setSmsId(lastId);
		int count = 0;
		for(int i : ids){
			PriSmsTo priSmsTo = new PriSmsTo();
			priSmsTo.setPriSms(priSms);
			priSmsTo.setDeleteFlag(1);
			priSmsTo.setReadFlag(1);
			priSmsTo.setToId(i);
			priSmsToMapper.add(priSmsTo);
			count++;
		}
		
		
		
		return count;
	}
	
	int lastId(){
		return priSmsMapper.lastId();
	}

	public PriSms findById(Integer smsId) {
		
		return priSmsMapper.findById(smsId);
	}

}
