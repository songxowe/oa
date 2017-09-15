package com.softfactory.core.dao;

import com.softfactory.pojo.PubMeetingRoom;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("pubMeetingRoomMapper")
public interface PubMeetingRoomMapper {

    @Insert("insert into pub_meeting_room (mr_name,mr_capacity,mr_device,mr_desc,mr_place) " +
            "values (#{mrName},#{mrCapacity},#{mrDevice},#{mrDesc},#{mrPlace})")
    Integer add(PubMeetingRoom pubMeetingRoom);

    @Update("update pub_meeting_room set mr_name=#{mrName},mr_capacity=#{mrCapacity},mr_device=#{mrDevice}," +
            "mr_desc=#{mrDesc},mr_place=#{mrPlace},useing_falg=#{useingFalg} where mr_id=#{mrId}")
    Integer modify(PubMeetingRoom pubMeetingRoom);

    List<PubMeetingRoom> findPager(@Param("pageNo")Integer pagerNo,
                                         @Param("pageSize")Integer pageSize,
                                         @Param("sort")String sort,
                                         @Param("order")String order,
                                         @Param("mrName")String mrName);

    Long getTotal(@Param("mrName")String mrName);

    PubMeetingRoom findById(@Param("mrId")Integer mrId);


    List<PubMeetingRoom> findRoomList();
}
