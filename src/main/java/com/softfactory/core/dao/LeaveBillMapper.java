package com.softfactory.core.dao;

import com.softfactory.pojo.LeaveBill;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 请假表mapper
 */
@Repository("leaveBillMapper")
public interface LeaveBillMapper {

    @Insert("insert into leave_bill (proposer,apply_date,dept_id,leave_type,start_date,end_date,leave_reason,current_step) " +
            "values (#{proposer},#{applyDate},#{hrDept.deptId},#{leaveType},#{startDate},#{endDate},#{leaveReason},#{currentStep})")
    Integer add(LeaveBill leaveBill);

    List<LeaveBill> findPager(@Param("pageNo") Integer pageNo,
                              @Param("pageSize") Integer pageSize,
                              @Param("sort") String sort,
                              @Param("order") String order,
                              @Param("beginDate") Date beginDate,
                              @Param("endDate") Date endDate,
                              @Param("proposer") String proposer);

    Long getTotal(@Param("beginDate") Date beginDate,
                  @Param("endDate") Date endDate,
                  @Param("proposer") String proposer);

    Integer getLastId();

    @Update("update leave_bill set current_step=#{status} where leave_id=#{id}")
    Integer updateStatus(@Param("status") String status, @Param("id") Integer id);

    LeaveBill findById(@Param("id") Integer id);

    @Update("update leave_bill set task_id=#{taskId} where leave_id=#{id}")
    Integer updateTaskId(@Param("taskId") String taskId, @Param("id") Integer id);


}
