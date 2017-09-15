package com.softfactory.core.test;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.softfactory.core.service.PriMailService;
import com.softfactory.core.service.PriMailToService;
import com.softfactory.core.util.Pager;
import com.softfactory.pojo.PriMail;
import com.softfactory.pojo.PriMailTo;

public class PriMailTest {
	private PriMailService priMailService;
	private PriMailToService priMailToService;

	@Before
	public void init() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

		priMailService = ctx.getBean("priMailService", PriMailService.class);
		priMailToService = ctx.getBean("priMailToService", PriMailToService.class);


	}

	@Test
	public void findPager() {
		// easyui 必须参数
		Integer page = 1;
		Integer rows = 3;
		// 计算开始行号和结束行号
		Integer pageno = (page - 1) * rows;
		Integer pagesize = page * rows;
		String sort = "sendTime";
		String order = "desc";
		Integer deleteFlag =0;
		Integer readFlag = null;
		String fromId = "";
		String toId = "曾凌";
		// 条件参数 可选
		Date beginDate = null;
		Date endDate = null;
		String sendFlag = "";
		String subject = "";
		String important = "";
		Pager<PriMailTo> pager = priMailToService.findPagerTwo(pageno, pagesize, sort, order,fromId,toId, subject, important,
				sendFlag, readFlag, deleteFlag, beginDate, endDate);
/*		Pager<PriMailTo> pager =priMailToService.findDrafts(pageno, pagesize, sort, order, fromId, toId, subject, important, beginDate, endDate);
*/		System.out.println("+++"+pager.getTotal());
		System.out.println("---"+pager.getRows().toString());
		System.out.println("--++-"+pager.toString());


	}

	@Test
	public void add() {
		PriMail priMail = new PriMail();
		PriMailTo priMailTo = new PriMailTo();
		priMail.setAttachment("OA.sql");// 附件名称
		priMail.setCopyTo("1");// 密送
		priMail.setFromId("");// 发件人
		priMail.setImportant("重要");// 重要程度
		priMail.setMailSize("22000");// 附件大小
		priMail.setSendTime(new Date());// 发送时间
		priMail.setSecreatTo("0");// 抄送
		priMail.setSendFlag("0");// 发送状态
		priMail.setSmsRemind("重要文件");// 提醒
		priMail.setContent("今天玩上要吃饭，快点来");// 邮件内容
		priMail.setToId("");// 收件人
		priMail.setSubject("吃法大事");// 邮件主题
		priMailTo.setDeleteFlag(1);// 删除状态
		priMailTo.setPriMail(priMail);
		priMailTo.setReadFlag(1);// 阅读状态
		priMailTo.setToId("");// 接收人
		priMailService.add(priMail);
	}

	@Test
	public void modify() {
		//priMail.setFromId("吴建辉");
		//priMail.setToId("张三");
		//priMail.setSendFlag("111");
		PriMailTo PriMailTo= priMailToService.findBymailToId(3);
		PriMail priMail = priMailService.findById(PriMailTo.getPriMail().getMailId());
		priMail.setSendFlag("5");
		//PriMailTo.setPriMail(priMail);
		//PriMailTo.setReadFlag(6);
		//PriMailTo.setDeleteFlag(7);
		 //int count =  priMailToService.modify(PriMailTo);
	int xx=priMailService.modify(priMail);
		System.out.println(xx);
		//System.out.println(PriMailTo.getMailToId()+PriMailTo.getDeleteFlag()+PriMailTo.getPriMail().getMailId());
	}

	@Test
	public void findById() {
		/*PriMail priMail = priMailService.findById(50);
		System.out.println(priMail.getFromId()+priMail.getImportant());*/
		PriMailTo PriMailTo=priMailToService.findBymailToId(50);
		System.out.println(PriMailTo.getReadFlag());
		
		/*
		PriMailTo	priMailTo = priMailToService.findById(50);
		priMailTo.setMailToId(50);
		priMailTo.setReadFlag(1);
		priMailToService.modify(priMailTo);*/
	}


}
