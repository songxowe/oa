package com.softfactory.core.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.softfactory.pojo.PubNotifyRevice;


@Repository("pubNotifyReviceMapper")
public interface PubNotifyReviceMapper {

	@Insert("insert into pub_notify_revice(notify_id,to_id,read_flag,delete_flag) values(#{pubNotify.notifyId},#{toId},#{readFlag},#{deleteFlag})")
	int add(PubNotifyRevice pubNotifyRevice);
	//按用户id查询所有公告信息
	@Select("select notify_id notifyId from pub_notify_revice where to_id=#{toId}")
	List<Integer> findById(Integer toId);
	//按用户id查询所有未读公告信息
	@Select("select notify_id notifyId from pub_notify_revice where to_id=#{toId} and read_flag=1 and delete_flag=1")
	List<Integer> findByIdRead(Integer toId);
	//修改阅读状态方法
	@Update("update pub_notify_revice set read_flag=2 where revice_id=#{reviceId}")
	int modifyReadFlag(@Param("reviceId") Integer reviceId);

	@Update("update pub_notify_revice set read_flag=2 where notify_id=#{notifyId} and to_id=#{toId}")
	int modifyReadFlag2(@Param("notifyId")Integer notifyId,@Param("toId")Integer toId);

	//按时间降序排列查询前五个公告
	
	List<PubNotifyRevice> findList(@Param("toId") Integer toId);
	
	List<PubNotifyRevice> findPager(
            @Param("pageno") Integer pageno,
            @Param("pagesize") Integer pagesize,
            @Param("sort") String sort,
            @Param("order") String order,
            @Param("subject") String subject,
            @Param("beginDate") Date beginDate,
            @Param("endDate") Date endDate,
            @Param("toId") Integer toId);
		
	int findPagerTotal(
            @Param("subject") String subject,
            @Param("beginDate") Date beginDate,
            @Param("endDate") Date endDate,
            @Param("toId") Integer toId);
	        
		}

	

