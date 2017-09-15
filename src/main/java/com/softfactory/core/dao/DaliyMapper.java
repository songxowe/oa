package com.softfactory.core.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import com.softfactory.pojo.Daliy;

/**
 * 个人日志mapper
 */
@Repository("daliyMapper")
public interface DaliyMapper {
    @Insert("insert into daliy(username,priopity,login_date,classmate,method,msg) values(#{username},#{priopity},#{logDate},#{classmate},#{method},#{msg})")
    int add(Daliy daliy);

    /**
     * 修改日志
     *
     * @param daliy
     * @return
     */
    @Update("update daliy set username=#{username},classmate=#{classmate},priopity=#{priopity},method=#{method},msg=#{msg} where id=#{id}")
    int modify(Daliy daliy);

    /**
     * 根据主键id删除日志
     *
     * @param id
     * @return
     */
    @Delete("delete from daliy where id=#{id}")
    int remove(Integer id);

    /**
     * 根据主键id查询日志
     *
     * @param id
     * @return
     */
    @Select("select id,username,priopity,log_date,classmate,method,msg from daliy where id=#{id}")
    Daliy findById(Integer id);

    @Select("select id,username,priopity,log_date,classmate,method,msg from daliy")
    List<Daliy> find();

    /**
     * 分页查询 + 条件查询
     *
     * @param pageno
     * @param pagesize
     * @param sort
     * @param order
     * @param
     * @return
     */
    List<Daliy> findPager(
            @Param("pageno") Integer pageno,
            @Param("pagesize") Integer pagesize,
            @Param("sort") String sort,
            @Param("order") String order,
            @Param("classmate") String classmate,
            @Param("beginDate") Date beginDate,
            @Param("endDate") Date endDate,
            @Param("username") String username);


    /**
     * 查询分页记录总数 + 条件查询
     *
     * @param ename
     * @return
     */
    Integer findPagerTotal(
            @Param("classmate") String classmate,
            @Param("beginDate") Date beginDate,
            @Param("endDate") Date endDate,
            @Param("username") String username);
}


