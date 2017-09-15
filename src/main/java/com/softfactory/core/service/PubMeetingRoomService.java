package com.softfactory.core.service;

import com.softfactory.core.dao.PubMeetingRoomMapper;
import com.softfactory.core.util.Pager;
import com.softfactory.pojo.PubMeetingRoom;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("pubMeetingRoomService")
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
public class PubMeetingRoomService {

    @Resource(name = "pubMeetingRoomMapper")
    private PubMeetingRoomMapper pubMeetingRoomMapper;

    public List<PubMeetingRoom> find() {
        return pubMeetingRoomMapper.findRoomList();
    }

    public PubMeetingRoom find(Integer mrId) {
        return pubMeetingRoomMapper.findById(mrId);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Integer modify(PubMeetingRoom pubMeetingRoom) {
        return pubMeetingRoomMapper.modify(pubMeetingRoom);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Integer add(PubMeetingRoom pubMeetingRoom) {
        return pubMeetingRoomMapper.add(pubMeetingRoom);
    }

    public Pager<PubMeetingRoom> find(Integer pageNo,Integer pageSize,String sort,String order,String mrName) {
        Pager<PubMeetingRoom> pager = new Pager<>();

        pager.setRows(pubMeetingRoomMapper.findPager(pageNo,pageSize,sort,order,mrName));
        pager.setTotal(pubMeetingRoomMapper.getTotal(mrName));

        return pager;
    }
}
