package com.softfactory.core.dao;

import com.softfactory.pojo.HrDept;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 部门表Mapper接口
 */
@Repository("hrDeptMapper")
public interface HrDeptMapper {

    @Insert("insert into hr_dept (dept_name,dept_tel,dept_func) values (#{deptName},#{deptTel},#{deptFunc})")
    Integer add(HrDept hrDept);

    @Update("update hr_dept set dept_name=#{deptName},dept_tel=#{deptTel},dept_func=#{deptFunc} where dept_id=#{deptId}")
    Integer modify(HrDept hrDept);

    HrDept findById(@Param("id") Integer id);

    List<HrDept> find();

    List<HrDept> findPager(@Param("pageNo") Integer pageNo,
                           @Param("pageSize") Integer pageSize,
                           @Param("sort") String sort,
                           @Param("order") String order,
                           @Param("deptName") String deptName);

    Long getTotal(@Param("deptName") String deptName);
}
