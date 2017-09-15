package com.softfactory.core.dao;

import com.softfactory.pojo.FlowVehicle;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("flowVehicleMapper")
public interface FlowVehicleMapper {

    @Insert("insert into flow_vehicle (checker,check_date,check_idea,check_status,vu_id) " +
            "values (#{checker},#{checkDate},#{checkIdea},#{checkStatus},#{vuId})")
    Integer add(FlowVehicle flowVehicle);

    List<FlowVehicle> findByVuId(@Param("vuId")Integer vuId);

}
