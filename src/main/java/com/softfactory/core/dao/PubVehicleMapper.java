package com.softfactory.core.dao;

import com.softfactory.pojo.PubVehicle;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 车辆CRUD
 */
@Repository("pubVehicleMapper")
public interface PubVehicleMapper {

    @Insert("insert into pub_vehicle (v_num,v_type,v_driver,v_price,buy_date,v_remark,v_status,useing_falg) " +
            "values (#{VNum},#{VType},#{VDriver},#{VPrice},#{buyDate},#{VRemark},#{VStatus},#{useingFalg})")
    Integer add(PubVehicle pubVehicle);

    @Update("update pub_vehicle set v_num=#{VNum},v_type=#{VType},v_driver=#{VDriver},buy_date=#{buyDate}," +
            "v_remark=#{VRemark},v_status=#{VStatus},useing_falg=#{useingFalg} where v_id=#{VId}")
    Integer modify(PubVehicle pubVehicle);


    List<PubVehicle> findPager(@Param("pageNo") Integer pageNo,
                               @Param("pageSize") Integer pageSize,
                               @Param("sort") String sort,
                               @Param("order") String order,
                               @Param("VNum") String VNum,
                               @Param("beginDate") Date beginDate,
                               @Param("endDate") Date endDate);

    Long getTotal(@Param("VNum") String VNum,
                  @Param("beginDate") Date beginDate,
                  @Param("endDate") Date endDate);

    PubVehicle findById(@Param("VId") Integer VId);

    List<PubVehicle> findCarList();

}
