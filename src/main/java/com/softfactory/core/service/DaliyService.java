package com.softfactory.core.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.softfactory.core.dao.DaliyMapper;
import com.softfactory.core.util.Pager;
import com.softfactory.pojo.Daliy;

@Service("daliyService")
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class DaliyService {
    @Resource(name = "daliyMapper")
    private DaliyMapper daliyMapper;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int add(Daliy daliy) {
        return daliyMapper.add(daliy);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int modify(Daliy daliy) {
        return daliyMapper.modify(daliy);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int remove(Integer id) {
        return daliyMapper.remove(id);
    }

    public Pager<Daliy> findPager(Integer pageno, Integer pagesize, String sort, String order, String classmate, Date beginDate, Date endDate, String username) {
        System.out.println("页号" + pageno);
        System.out.println("页大小" + pagesize);
        System.out.println("排序字段" + sort);
        System.out.println("排序方式" + order);
        System.out.println("用户名" + username);
        System.out.println("类型" + classmate);
        System.out.println("起始时间" + beginDate);
        System.out.println("结束时间" + endDate);


        Pager<Daliy> pager = new Pager<Daliy>();
        // 设置分页数据
        pager.setRows(daliyMapper.findPager(pageno, pagesize, sort, order, classmate, beginDate, endDate, username));
        // 设置数据总数
        pager.setTotal(daliyMapper.findPagerTotal(classmate, beginDate, endDate, username));
        return pager;
    }

    public List<Daliy> find() {
        return daliyMapper.find();
    }

    public Daliy findById(Integer id) {
        return daliyMapper.findById(id);
    }
}
