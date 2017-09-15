package com.softfactory.core.service;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.softfactory.core.dao.PriMailMapper;
import com.softfactory.core.dao.PriMailToMapper;
import com.softfactory.pojo.PriMail;
import com.softfactory.pojo.PriMailTo;


@Service("priMailService")
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class PriMailService {
	@Resource(name="priMailMapper")
	private PriMailMapper priMailMapper;
	
	@Resource(name="priMailToMapper")
	private PriMailToMapper priMailToMapper;

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public Integer add(PriMail PriMail) {
		// TODO Auto-generated method stub
		Integer num = priMailMapper.addPriMail(PriMail);

			Integer mailId = priMailMapper.selectId();
			PriMail.setMailId(mailId);
			PriMailTo priMailTo = new PriMailTo();
			priMailTo.setPriMail(PriMail);
			priMailTo.setDeleteFlag(0);
			priMailTo.setReadFlag(0);
			priMailTo.setToId(PriMail.getToId());
			priMailToMapper.add(priMailTo);

	
		return num;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int modify(PriMail priMail ) {
		
		return priMailMapper.modify(priMail);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int modifyBy(PriMail priMail ) {
		
		return priMailMapper.modifyBy(priMail);
	}

	public Integer selectId(){
		return priMailMapper.selectId();
	}

	

	public PriMail findById(Integer mailId) {
		return priMailMapper.findById(mailId);
	}

}
