package com.softfactory.core.dao;


import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.softfactory.pojo.PriMailTo;

/**
 * 邮件相关数据操作
 */
@Repository("priMailToMapper")
public interface PriMailToMapper {

    @Insert("insert into Pri_Mail_To(MAIL_ID,TO_ID,READ_FLAG,DELETE_FLAG)values (#{priMail.mailId},#{toId},#{readFlag },#{deleteFlag})")
    int add(PriMailTo PriMailTo);

    //@Select("select mail_To_Id mailToId,mail_Id mailId,TO_ID,READ_FLAG readFlag,DELETE_FLAG deleteFlag from Pri_Mail_To  where mail_To_Id = #{mailToId}")
    PriMailTo findBymailToId(@Param("mailToId") Integer mailToId);

    //修改状态（整体修改）
    @Update("update Pri_Mail_To set READ_FLAG = #{readFlag},delete_Flag = #{deleteFlag} ,to_ID =#{toId} where   mail_To_Id=#{mailToId}")
    int modify(PriMailTo PriMailTo);
    // 类中的属性和标准的字段名不一致的时候，必须给字段取别名

    // 开始分页
    // 基本分页查询
    List<PriMailTo> findPager(@Param("pageno") Integer pageno, // 当前第几页（开始行号）
                              @Param("pagesize") Integer pagesize, // 结束行号
                              @Param("sort") String sort, // 排序字段
                              @Param("order") String order, // 排序方式
                              @Param("fromId") String fromId, // 条件参数
                              @Param("toId") String toId, // 条件参数
                              @Param("subject") String subject, // 条件参数
                              @Param("important") String important, // 条件参数
                              @Param("sendFlag") String sendFlag, // 条件参数
                              @Param("deleteFlag") Integer deleteFlag, // 条件参数
                              @Param("readFlag") Integer readFlag, // 条件参数
                              @Param("beginDate") Date beginDate, @Param("endDate") Date endDate);

    int getTotal(@Param("fromId") String fromId, @Param("toId") String toID, @Param("subject") String subject, @Param("important") String important, @Param("sendFlag") String sendFlag, @Param("deleteFlag") Integer deleteFlag, @Param("readFlag") Integer readFlag, @Param("beginDate") Date beginDate, @Param("endDate") Date endDate);


    // 开始分页
    // 邮件箱分页查询
    List<PriMailTo> findDrafts(@Param("pageno") Integer pageno, // 当前第几页（开始行号）
                               @Param("pagesize") Integer pagesize, // 结束行号
                               @Param("sort") String sort, // 排序字段
                               @Param("order") String order, // 排序方式
                               @Param("fromId") String fromId, // 条件参数
                               @Param("toId") String toId, // 条件参数
                               @Param("subject") String subject, // 条件参数
                               @Param("important") String important, // 条件参数
                               @Param("beginDate") Date beginDate, @Param("endDate") Date endDate);

    int findTotal(@Param("fromId") String fromId, @Param("toId") String toID, @Param("subject") String subject, @Param("important") String important, @Param("beginDate") Date beginDate, @Param("endDate") Date endDate);


    // 收件箱基本分页查询
    List<PriMailTo> findPagerTwo(@Param("pageno") Integer pageno, // 当前第几页（开始行号）
                                 @Param("pagesize") Integer pagesize, // 结束行号
                                 @Param("sort") String sort, // 排序字段
                                 @Param("order") String order, // 排序方式
                                 @Param("fromId") String fromId, // 条件参数
                                 @Param("toId") String toId, // 条件参数
                                 @Param("subject") String subject, // 条件参数
                                 @Param("important") String important, // 条件参数
                                 @Param("sendFlag") String sendFlag, // 条件参数
                                 @Param("deleteFlag") Integer deleteFlag, // 条件参数
                                 @Param("readFlag") Integer readFlag, // 条件参数
                                 @Param("beginDate") Date beginDate, @Param("endDate") Date endDate);

    int getTotalTwo(@Param("fromId") String fromId, @Param("toId") String toID, @Param("subject") String subject, @Param("important") String important, @Param("sendFlag") String sendFlag, @Param("readFlag") Integer readFlag, @Param("deleteFlag") Integer deleteFlag, @Param("beginDate") Date beginDate, @Param("endDate") Date endDate);


    int findreadCount(@Param("userTrueName")String userTrueName);

}
