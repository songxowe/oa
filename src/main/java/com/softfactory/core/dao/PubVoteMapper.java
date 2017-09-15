package com.softfactory.core.dao;


import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.softfactory.pojo.PubVote;

/**
 * 投票CRUD
 */
@Repository("pubVoteMapper")
public interface PubVoteMapper {

    int deleteByPrimaryKey(Integer voteId);

    int insert(PubVote record);

    int insertSelective(PubVote record);

    PubVote selectByPrimaryKey(Integer voteId);

    int updateByPrimaryKeySelective(PubVote record);

    int updateByPrimaryKey(PubVote record);

    /*
     * 获得最新insert语句有自动增长的ID
     */
    // show table status;不建议使用
    //@Select("select @@identity") -- 没有返回空
    @Select("select last_insert_id()")
    Integer selectId();

    Integer getTotal(@Param("voteStatus") String voteStatus, @Param("subject") String subject, @Param("beginDate") Date beginDate, @Param("endDate") Date endDate);

    List<PubVote> findPage(@Param("start") Integer start, @Param("rows") Integer rows, @Param("sort") String sort, @Param("order") String order, @Param("voteStatus") String voteStatus, @Param("subject") String subject, @Param("beginDate") Date beginDate, @Param("endDate") Date endDate);

    List<PubVote> findPageBefore(@Param("start") Integer start, @Param("rows") Integer rows, @Param("sort") String sort, @Param("order") String order, @Param("voteStatus") String voteStatus, @Param("subject") String subject, @Param("beginDate") Date beginDate, @Param("endDate") Date endDate);
}