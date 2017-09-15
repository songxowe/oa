package com.softfactory.core.service;

import com.softfactory.core.dao.HrEmpMapper;
import com.softfactory.core.util.Pager;
import com.softfactory.pojo.HrEmp;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service("hrEmpService")
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
public class HrEmpService {
    @Resource(name = "hrEmpMapper")
    private HrEmpMapper hrEmpMapper;

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Integer add(HrEmp hrEmp) {
        return hrEmpMapper.add(hrEmp);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Integer modify(HrEmp hrEmp) {
        return hrEmpMapper.modify(hrEmp);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Integer updateStatus(Integer status,Integer id) {
        return hrEmpMapper.updateStatus(status,id);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Integer remove(Integer id) {
        return hrEmpMapper.remove(id);
    }

    public Integer getLastId() {
        return hrEmpMapper.getLastId()+1;
    }

    public Pager<HrEmp> find(Integer pageNo, Integer pageSize, String sort, String order, String empName, String workId, Integer deptId, Date beginDate,Date endDate,Integer status) {
        Pager<HrEmp> pager = new Pager<>();

        pager.setRows(hrEmpMapper.findPager(pageNo,pageSize,sort,order,empName,workId,deptId,beginDate,endDate,status));
        pager.setTotal(hrEmpMapper.getTotal(empName,workId,deptId,beginDate,endDate,status));

        return pager;
    }

    public HrEmp find(Integer id) {
        return hrEmpMapper.findById(id);
    }

}
