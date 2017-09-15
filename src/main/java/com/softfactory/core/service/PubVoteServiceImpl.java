package com.softfactory.core.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.softfactory.core.dao.PubVoteMapper;
import com.softfactory.core.dao.PubVoteSubMapper;
import com.softfactory.core.util.Pager;
import com.softfactory.pojo.PubVote;
import com.softfactory.pojo.PubVoteSub;



@Service("pubVoteService")
@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
public class PubVoteServiceImpl implements PubVoteService {
	
	@Resource(name="pubVoteMapper")
	private PubVoteMapper pubVoteMapper;
	@Resource(name="pubVoteSubMapper")
	private PubVoteSubMapper pubVoteSubMapper;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public Integer insert(PubVote record) {
		// TODO Auto-generated method stub
		return pubVoteMapper.insert(record);
	}

	@Override
	public PubVote selectByPrimaryKey(Integer voteId) {
		// TODO Auto-generated method stub
		return pubVoteMapper.selectByPrimaryKey(voteId);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public Integer updateByPrimaryKey(PubVote record) {
		// TODO Auto-generated method stub
		return pubVoteMapper.updateByPrimaryKey(record);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public Integer add(PubVote pubVote, String ids[]) {
		// TODO Auto-generated method stub
		Integer num=pubVoteMapper.insert(pubVote);
		//获得当前插入语句自动增长值
		Integer voteId=pubVoteMapper.selectId();
		System.out.println("当前自动增长值为: "+voteId);
		if(num>0) {
			for(String title:ids) {
				//System.out.println(title);
				PubVoteSub pvs=new PubVoteSub();
				pvs.setVoteId(voteId);
				pvs.setTitle(title);
				pvs.setDescn("选我,没毛病老铁!");
				pvs.setVoteCount(0);
				pubVoteSubMapper.insert(pvs);
				num++;
			}
		}
		return num;
	}

	@Override
	public Pager<PubVote> findPage(Integer page, Integer rows, String sort, String order,String voteStatus, String subject,
			Date beginDate, Date endDate) {
		System.out.println("参数: 页号: "+page);
		System.out.println("参数: 页大小: "+rows);
		System.out.println("参数: 那个字段排序: "+sort);
		System.out.println("参数: 排序方式: "+order);
		System.out.println("模糊查询的名: "+subject);
		System.out.println("起始时间: "+beginDate);
		System.err.println("起始时间: "+endDate);
		System.out.println("-------*******------");
		Integer start=(page-1)*rows;
		// TODO Auto-generated method stub
		Pager<PubVote>pager=new Pager<>();
		List<PubVote> list=pubVoteMapper.findPage(start, rows, sort, order, voteStatus, subject, beginDate, endDate);
		Integer total=pubVoteMapper.getTotal(voteStatus,subject, beginDate, endDate);
		pager.setRows(list);
		pager.setTotal(total);
		return pager;
	}

	@Override
	public List<PubVote> findPageBefore(Integer page, Integer rows, String sort, String order, String voteStatus,
			String subject, Date beginDate, Date endDate) {
		// TODO Auto-generated method stub
		return pubVoteMapper.findPageBefore(page, rows, sort, order, voteStatus, subject, beginDate, endDate);
	}
	
	

}
