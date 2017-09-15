package com.softfactory.core.dao;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.softfactory.pojo.PriMail;
import com.softfactory.pojo.PriMailTo;


@Repository("priMailMapper")
public interface PriMailMapper {

    @Insert("insert into Pri_Mail(from_Id,to_Id,copy_To,secreat_To,subject,content,send_Time,attachment,send_Flag,sms_Remind,important,mail_Size)values (#{fromId},#{toId},#{copyTo },#{secreatTo },#{subject },#{content },#{sendTime,jdbcType=DATE},#{attachment },#{sendFlag },#{smsRemind },#{important },#{mailSize })")
    int addPriMail(PriMail PriMail);

    /*
     * 获得最新insert语句有增长的Id
     * show table status 不建议使用
     * @select("select @@identity")没有返回空
     */
    @Select("select last_insert_id()")
    Integer selectId();


    //查询邮件信息
    @Select("select mail_Id mailId,from_Id fromId,to_Id toId,copy_To copyTo,secreat_To secreatTo,subject,content,send_Time sendTime,attachment,send_Flag sendFlag,sms_Remind smsRemind,important,mail_Size mailSize from Pri_Mail where mail_Id=#{mailId}")
    PriMail findById(@Param("mailId") Integer mailId);


    //修改状态（整体修改）
    @Update("update Pri_Mail set FROM_ID=#{fromId},to_Id=#{toId},copy_To=#{copyTo},secreat_To=#{secreatTo},subject=#{subject },content=#{content },send_Time=#{sendTime,jdbcType=DATE},attachment=#{attachment },send_Flag=#{sendFlag },sms_Remind=#{smsRemind },important=#{important },mail_Size=#{mailSize }  where mail_Id =#{mailId}")
    int modify(PriMail PriMail);

    //修改状态（整体修改）
    @Update("update Pri_Mail set send_Flag=#{sendFlag } where mail_Id =#{mailId}")
    int modifyBy(PriMail PriMail);


}
