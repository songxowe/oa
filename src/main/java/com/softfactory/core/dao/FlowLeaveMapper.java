package com.softfactory.core.dao;

import com.softfactory.pojo.FlowLeave;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("flowLeaveMapper")
public interface FlowLeaveMapper {

    @Insert("insert into flow_leave (checker,check_date,check_idea,check_status,leave_id) " +
            "values (#{checker},#{checkDate},#{checkIdea},#{checkStatus},#{leaveId})")
    Integer add(FlowLeave flowLeave);

    List<FlowLeave> findByLeaveId(@Param("leaveId")Integer leaveId);
}
