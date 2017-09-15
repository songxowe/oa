package com.softfactory.core.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.softfactory.pojo.PriSms;

@Repository("priSmsMapper")
public interface PriSmsMapper {

	@Insert("insert into pri_sms(from_id,sms_type,content,send_time,remind_url) values(#{fromId},#{smsType},#{content},#{sendTime},#{remindUrl})")
	int add(PriSms priSms);
	
	@Select("select last_insert_id()")
	int lastId();
	
	@Select("select sms_id smsId,from_id fromId,sms_type smsType,content,send_time sendTime,remind_url remindUrl from pri_sms where sms_id=#{smsId}")
	PriSms findById(@Param("smsId") Integer smsId);
}
