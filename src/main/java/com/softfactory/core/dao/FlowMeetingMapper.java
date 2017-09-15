package com.softfactory.core.dao;

import com.softfactory.pojo.FlowMeeting;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("flowMeetingMapper")
public interface FlowMeetingMapper {

    @Insert("insert into flow_meeting (checker,check_date,check_idea,check_status,mu_id) " +
            "values (#{checker},#{checkDate},#{checkIdea},#{checkStatus},#{muId})")
    Integer add(FlowMeeting flowMeeting);

    List<FlowMeeting> findByMeetingId(@Param("muId")Integer muId);

}
