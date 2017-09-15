package com.softfactory.core.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.softfactory.core.dao.PriMailToMapper;
import com.softfactory.core.util.Pager;
import com.softfactory.pojo.PriMail;
import com.softfactory.pojo.PriMailTo;


@Service("priMailToService")
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class PriMailToService {

    @Resource(name = "priMailToMapper")
    private PriMailToMapper priMailToMapper;


    public PriMailTo findBymailToId(Integer mailToId) {
        // TODO Auto-generated method stub
        return priMailToMapper.findBymailToId(mailToId);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public int modify(PriMailTo PriMailTo) {
        // TODO Auto-generated method stub
        return priMailToMapper.modify(PriMailTo);
    }


    public Pager<PriMailTo> findPager(Integer pageno,
                                      Integer pagesize,
                                      String sort,
                                      String order,
                                      String fromId,
                                      String toId,
                                      String subject,
                                      String important,
                                      String sendFlag,
                                      Integer readFlag,
                                      Integer deleteFlag,
                                      Date beginDate,
                                      Date endDate) {
        Pager<PriMailTo> pager = new Pager<>();
        //封装分页查询结果集
        pager.setRows(priMailToMapper.findPager(pageno, pagesize, sort, order, fromId, toId, subject, important, sendFlag, deleteFlag, readFlag, beginDate, endDate));
        //封装分页总页数
        pager.setTotal(priMailToMapper.getTotal(fromId, toId, subject, important, sendFlag, deleteFlag, readFlag, beginDate, endDate));
        return pager;
    }

    //收件箱分页查询
    public Pager<PriMailTo> findPagerTwo(Integer pageno,
                                         Integer pagesize,
                                         String sort,
                                         String order,
                                         String fromId,
                                         String toId,
                                         String subject,
                                         String important,
                                         String sendFlag,
                                         Integer readFlag,
                                         Integer deleteFlag,
                                         Date beginDate,
                                         Date endDate) {
        Pager<PriMailTo> pager = new Pager<>();
        //封装分页查询结果集
        pager.setRows(priMailToMapper.findPagerTwo(pageno, pagesize, sort, order, fromId, toId, subject, important, sendFlag,readFlag, deleteFlag,  beginDate, endDate));
        //封装分页总页数
        pager.setTotal(priMailToMapper.getTotalTwo(fromId, toId, subject, important, sendFlag, readFlag,deleteFlag,  beginDate, endDate));
        return pager;
    }


    //草稿箱分页查询
    public Pager<PriMailTo> findDrafts(Integer pageno,
                                       Integer pagesize,
                                       String sort,
                                       String order,
                                       String fromId,
                                       String toId,
                                       String subject,
                                       String important,
                                       Date beginDate,
                                       Date endDate) {
        Pager<PriMailTo> pager = new Pager<>();
        //封装分页查询结果集
        pager.setRows(priMailToMapper.findDrafts(pageno, pagesize, sort, order, fromId, toId, subject, important, beginDate, endDate));
        //封装分页总页数
        pager.setTotal(priMailToMapper.findTotal(fromId, toId, subject, important, beginDate, endDate));
        return pager;
    }


   public int findreadCount(String userTrueName){
        return  priMailToMapper.findreadCount(userTrueName);
   };
}
