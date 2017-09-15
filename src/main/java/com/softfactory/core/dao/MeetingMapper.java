package com.softfactory.core.dao;

import com.softfactory.pojo.Meeting;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository("meetingMapper")
public interface MeetingMapper {

    @Insert("insert into meeting (proposer,apply_date,m_room,m_topic,m_desc,m_attendee,m_start,m_end,current_step) " +
            "values (#{proposer},#{applyDate},#{meetingRoom.mrId},#{MTopic},#{MDesc},#{MAttendee},#{MStart},#{MEnd},#{currentStep})")
    Integer add(Meeting meeting);

    List<Meeting> findPager(@Param("pageNo")Integer pageNo,
                            @Param("pageSize")Integer pageSize,
                            @Param("sort")String sort,
                            @Param("order")String order,
                            @Param("proposer")String proposer,
                            @Param("MTopic")String MTopic,
                            @Param("beginDate")Date beginDate,
                            @Param("endDate")Date endDate);

    Long getTotal(@Param("proposer")String proposer,
                  @Param("MTopic")String MTopic,
                  @Param("beginDate")Date beginDate,
                  @Param("endDate")Date endDate);

    @Update("update meeting set task_id=#{taskId} where id=#{id}")
    Integer updateTaskId(@Param("taskId") String taskId,@Param("id") Integer id);

    Integer getLastId();

    Meeting findById(@Param("id")Integer id);

    @Update("update meeting set current_step=#{status} where id=#{id}")
    Integer updateStatus(@Param("status")String status,@Param("id")Integer id);
}
