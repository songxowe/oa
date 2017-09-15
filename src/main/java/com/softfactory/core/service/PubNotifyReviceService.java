package com.softfactory.core.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.softfactory.core.dao.PubNotifyReviceMapper;
import com.softfactory.core.util.Pager;
import com.softfactory.pojo.PubNotify;
import com.softfactory.pojo.PubNotifyRevice;
@Service("pubNotifyReviceService")
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class PubNotifyReviceService {
	@Resource(name="pubNotifyReviceMapper")
	private PubNotifyReviceMapper pubNotifyReviceMapper;
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int add(PubNotifyRevice pubNotifyRevice){
		return pubNotifyReviceMapper.add(pubNotifyRevice);
	}

	public List<Integer> findById(Integer toId){
		return pubNotifyReviceMapper.findById(toId);
	}
	
	 public Pager<PubNotifyRevice> find(Integer page, Integer rows, String sort, String order, String subject,Integer notifyId, Date beginDate, Date endDate,Integer toId) {
		    Pager<PubNotifyRevice> pager = new Pager<>();
		    pager.setRows(pubNotifyReviceMapper.findPager(page, rows, sort, order, subject, beginDate, endDate, toId));
		    pager.setTotal(pubNotifyReviceMapper.findPagerTotal(subject, beginDate, endDate,toId));
		    return pager;
		  }

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	 public void modifyReadFlag(Integer reviceId){
		 pubNotifyReviceMapper.modifyReadFlag(reviceId);
	 }

	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void modifyReadFlag(Integer notifyId,Integer toId) {
	 	pubNotifyReviceMapper.modifyReadFlag2(notifyId,toId);
	}
	 
	 /*
	  * 按时间降序排列查询前五个公告
	  */
	 public List<PubNotifyRevice> findList(Integer toId){
		 return pubNotifyReviceMapper.findList(toId);
	 }
}
