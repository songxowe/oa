package com.softfactory.core.service;

import com.softfactory.core.dao.PubVehicleMapper;
import com.softfactory.core.util.Pager;
import com.softfactory.pojo.PubVehicle;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 车辆信息表service
 */
@Service("pubVehicleService")
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
public class PubVehicleService {
    @Resource(name = "pubVehicleMapper")
    private PubVehicleMapper pubVehicleMapper;

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Integer add(PubVehicle pubVehicle) {
        return pubVehicleMapper.add(pubVehicle);
    }
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Integer modify(PubVehicle pubVehicle) {
        return pubVehicleMapper.modify(pubVehicle);
    }

    public Pager<PubVehicle> find(Integer pageNo, Integer pageSize, String sort, String order, String VNum, Date beginDate,Date endDate) {
        Pager<PubVehicle> pager = new Pager<>();
        pager.setRows(pubVehicleMapper.findPager(pageNo,pageSize,sort,order,VNum,beginDate,endDate));
        pager.setTotal(pubVehicleMapper.getTotal(VNum,beginDate,endDate));
        return pager;
    }

    public List<PubVehicle> find() {
        return pubVehicleMapper.findCarList();
    }

    public PubVehicle find(Integer VId) {
        return pubVehicleMapper.findById(VId);
    }

}
