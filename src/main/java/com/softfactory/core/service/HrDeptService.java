package com.softfactory.core.service;

import com.softfactory.core.dao.HrDeptMapper;
import com.softfactory.core.util.Pager;
import com.softfactory.pojo.HrDept;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.List;

@Service("hrDeptService")
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
public class HrDeptService {
    @Resource(name = "hrDeptMapper")
    private HrDeptMapper hrDeptMapper;

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Integer add(HrDept hrDept) {
        return hrDeptMapper.add(hrDept);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Integer modify(HrDept hrDept) {
        return hrDeptMapper.modify(hrDept);
    }

    public Pager<HrDept> find(Integer pageNo,Integer pageSize,String sort,String order,String deptName) {
        Pager<HrDept> pager = new Pager<>();

        pager.setRows(hrDeptMapper.findPager(pageNo,pageSize,sort,order,deptName));
        pager.setTotal(hrDeptMapper.getTotal(deptName));

        return pager;
    }

    public List<HrDept> find() {
        return hrDeptMapper.find();
    }

    public HrDept find(Integer id) {
        return hrDeptMapper.findById(id);
    }

}
