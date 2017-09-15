package com.softfactory.core.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;


import com.softfactory.pojo.PriSmsTo;

/**
 * 短信相关数据操作
 */
@Repository("priSmsToMapper")
public interface PriSmsToMapper {

    @Insert("insert into pri_sms_to(sms_id,to_id,read_flag,delete_flag) values(#{priSms.smsId},#{toId},#{readFlag},#{deleteFlag})")
    int add(PriSmsTo priSmsTo);

    @Select("select count(sms_to_id) from pri_sms_to where to_id=#{toId} and read_flag=1")
    int findByReadFlag(@Param("toId") Integer toId);

    //修改阅读状态方法
    @Update("update pri_sms_to set read_flag=2 where sms_to_id=#{smsToId}")
    int modifyReadFlag(@Param("smsToId") Integer smsToId);

    //删除短信
    @Update("update pri_sms_to set delete_flag=2 where sms_id=#{smsId} and to_id=#{toId}")
    int removePriSms(@Param("smsId") Integer smsId, @Param("toId") Integer toId);

    List<PriSmsTo> findPager(
            @Param("pageno") Integer pageno,
            @Param("pagesize") Integer pagesize,
            @Param("sort") String sort,
            @Param("order") String order,
            @Param("smsType") String smsType,
            @Param("beginDate") Date beginDate,
            @Param("endDate") Date endDate,
            @Param("toId") Integer toId,
            @Param("readFlag") Integer readFlag,
            @Param("fromId") Integer fromId);

    int findPagerTotal(
            @Param("smsType") String smsType,
            @Param("beginDate") Date beginDate,
            @Param("endDate") Date endDate,
            @Param("toId") Integer toId,
            @Param("readFlag") Integer readFlag,
            @Param("fromId") Integer fromId);

	/*
	 * 页面实时刷新查询
	 */

    PriSmsTo findBySendTime(@Param("newDate") Date newDate, @Param("toId") Integer toId);

}
