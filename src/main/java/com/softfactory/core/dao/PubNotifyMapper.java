package com.softfactory.core.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.softfactory.pojo.PubNotify;

/**
 * 公告数据相关操作
 */
@Repository("pubNotifyMapper")
public interface PubNotifyMapper {
    @Insert("insert into pub_notify(subject,content,from_id,create_time,attachment,notify_status) values(#{subject},#{content},#{fromId},#{createTime},#{attachment},#{notifyStatus})")
    int add(PubNotify notify);

    @Update("update pub_notify set subject=#{subject},content=#{content},create_time=#{createTime},attachment=#{attachment},notify_status=#{notifyStatus} where notify_id=#{notifyId}")
    int modify(PubNotify notify);

    @Update("update pub_notify set notify_status='过期' where notify_id=#{notifyId}")
    int remove(Integer notifyId);

    @Select("select subject,content,from_id fromId,create_time createTime,attachment,notify_status notifyStatus from pub_notify where notify_id=#{notifyId}")
    PubNotify findById(Integer notifyId);

    @Select("select subject,content,from_id,create_time,attachment,notify_status from pub_notify")
    List<PubNotify> find();


    List<PubNotify> findByParam(
            @Param("subject") String subject,
            @Param("beginDate") Date beginDate,
            @Param("endDate") Date endDate);

    List<PubNotify> findPager(
            @Param("page") Integer page,
            @Param("rows") Integer rows,
            @Param("sort") String sort,
            @Param("order") String order,
            @Param("subject") String subject,
            @Param("beginDate") Date beginDate,
            @Param("endDate") Date endDate);

    int getTotal(
            @Param("subject") String subject,
            @Param("beginDate") Date beginDate,
            @Param("endDate") Date endDate);

    List<PubNotify> findPagerUser(
            @Param("page") Integer page,
            @Param("rows") Integer rows,
            @Param("sort") String sort,
            @Param("order") String order,
            @Param("subject") String subject,
            @Param("beginDate") Date beginDate,
            @Param("endDate") Date endDate,
            @Param("toId") Integer toId);

    int getTotalUser(
            @Param("subject") String subject,
            @Param("beginDate") Date beginDate,
            @Param("endDate") Date endDate,
            @Param("toId") Integer toId);

    @Select("select last_insert_id()")
    int lastId();
}
