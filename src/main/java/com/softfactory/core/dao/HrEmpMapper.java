package com.softfactory.core.dao;

import com.softfactory.pojo.HrEmp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 档案表mapper接口
 */
@Repository("hrEmpMapper")
public interface HrEmpMapper {

    Integer add(HrEmp hrEmp);

    Integer modify(HrEmp hrEmp);

    List<HrEmp> findPager(@Param("pageNo") Integer pageNo,
                          @Param("pageSize") Integer pageSize,
                          @Param("sort") String sort,
                          @Param("order") String order,
                          @Param("empName") String empName,
                          @Param("workId") String workId,
                          @Param("deptId") Integer deptId,
                          @Param("beginDate") Date beginDate,
                          @Param("endDate") Date endDate,
                          @Param("status") Integer status);

    Long getTotal(@Param("empName") String empName,
                  @Param("workId") String workId,
                  @Param("deptId") Integer deptId,
                  @Param("beginDate") Date beginDate,
                  @Param("endDate") Date endDate,
                  @Param("status") Integer status);

    Integer getLastId();

    HrEmp findById(@Param("id") Integer id);

    @Update("update hr_emp set status=#{status} where emp_id=#{id}")
    Integer updateStatus(@Param("status") Integer status, @Param("id") Integer id);

    @Delete("delete from hr_emp where emp_id=#{id}")
    Integer remove(@Param("id") Integer id);

}
