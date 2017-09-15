package com.softfactory.core.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.softfactory.core.dao.PriSmsMapper;
import com.softfactory.core.dao.PriSmsToMapper;
import com.softfactory.core.dao.PubNotifyMapper;
import com.softfactory.core.dao.PubNotifyReviceMapper;
import com.softfactory.core.dao.UserMapper;
import com.softfactory.pojo.PriSms;
import com.softfactory.pojo.PriSmsTo;
import com.softfactory.pojo.PubNotify;
import com.softfactory.pojo.PubNotifyRevice;
import com.softfactory.core.util.Pager;

@Service("pubNotifyService")
@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
public class PubNotifyService {
	
	@Resource(name="pubNotifyMapper")
	private PubNotifyMapper pubNotifyMapper;
	@Resource(name="pubNotifyReviceMapper")
	private PubNotifyReviceMapper pubNotifyReviceMapper;
	@Resource(name="priSmsMapper")
	private PriSmsMapper priSmsMapper;
	@Resource(name="priSmsToMapper")
	private PriSmsToMapper priSmsToMapper;
	@Resource(name="userMapper")
	private UserMapper userMapper;
	/*
	 * 新增公告方法，四表同时添加
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	  public int add(PubNotify notify) {
		int count = pubNotifyMapper.add(notify);
		int notifyId = pubNotifyMapper.lastId();
		notify.setNotifyId(notifyId);
		List<Integer> users = userMapper.findAllId();
		
			
			for(Integer userId : users){
				PubNotifyRevice pnr = new PubNotifyRevice();
				pnr.setToId(userId);
				pnr.setPubNotify(notify);
				pnr.setDeleteFlag(1);
				pnr.setReadFlag(1);
				pubNotifyReviceMapper.add(pnr);
			}
			
			
		
		
		PriSms priSms = new PriSms();
		priSms.setContent("您有一条新公告");
		priSms.setFromId(notify.getFromId());
		priSms.setSendTime(new Date());
		priSms.setSmsType("系统消息");
		
		priSmsMapper.add(priSms);
		int smsId = priSmsMapper.lastId();
		priSms.setSmsId(smsId);
			for(Integer userId : users){
				PriSmsTo pst = new PriSmsTo();
				pst.setDeleteFlag(1);
				pst.setReadFlag(1);
				pst.setPriSms(priSms);
				pst.setToId(userId);
				priSmsToMapper.add(pst);
			}
			
			return count;
		
	  }
	
	public int lastId(){
		return pubNotifyMapper.lastId();
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	  public int modify(PubNotify notify) {
	    return pubNotifyMapper.modify(notify);
	  }
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	  public int remove(Integer notifyId) {
	    return pubNotifyMapper.remove(notifyId);
	  }
	
	
	  public PubNotify findById(Integer notifyId) {
	    return pubNotifyMapper.findById(notifyId);
	  }

	  public List<PubNotify> find() {
		    return pubNotifyMapper.find();
		  }

	  public List<PubNotify> find(String subject, Date beginDate, Date endDate) {
		    return pubNotifyMapper.findByParam(subject, beginDate, endDate);
		  }

	  public Pager<PubNotify> find(Integer page, Integer rows, String sort, String order, String subject, Date beginDate, Date endDate) {
		    Pager<PubNotify> pager = new Pager<>();
		    pager.setRows(pubNotifyMapper.findPager(page, rows, sort, order, subject, beginDate, endDate));
		    pager.setTotal(pubNotifyMapper.getTotal(subject, beginDate, endDate));
		    return pager;
		  }


}
