package com.softfactory.core.dao;

import com.softfactory.pojo.VehicleUsage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 车辆申请CRUD
 */
@Repository("vehicleUsageMapper")
public interface VehicleUsageMapper {

    @Insert("insert into vehicle_usage (proposer,apply_date,dept_id,v_id,vu_reason,vu_start,vu_end,current_step) values " +
            "(#{proposer},#{applyDate},#{hrDept.deptId},#{pubVehicle.VId},#{vuReason},#{vuStart},#{vuEnd},#{currentStep})")
    Integer add(VehicleUsage vehicleUsage);

    List<VehicleUsage> findPager(@Param("pageNo") Integer pageNo,
                                 @Param("pageSize") Integer pageSize,
                                 @Param("sort") String sort,
                                 @Param("order") String order,
                                 @Param("proposer") String proposer,
                                 @Param("beginDate") Date beginDate,
                                 @Param("endDate") Date endDate);

    Long getTotal(@Param("proposer") String proposer,
                  @Param("beginDate") Date beginDate,
                  @Param("endDate") Date endDate);

    VehicleUsage findById(@Param("id") Integer id);

    Integer getLastId();

    @Update("update vehicle_usage set task_id=#{taskId} where id=#{id}")
    Integer updateTaskId(@Param("taskId") String taskId, @Param("id") Integer id);

    @Update("update vehicle_usage set current_step=#{status} where id=#{id}")
    Integer updateStatus(@Param("status") String status, @Param("id") Integer id);
}
