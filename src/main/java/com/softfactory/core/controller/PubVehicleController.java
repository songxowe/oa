package com.softfactory.core.controller;

import com.softfactory.core.service.PubVehicleService;
import com.softfactory.core.util.JsonDateValueProcessor;
import com.softfactory.core.util.Pager;
import com.softfactory.pojo.PubVehicle;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 车辆管理controller
 */
@SuppressWarnings("all")
@Controller
public class PubVehicleController {

    @Resource(name = "pubVehicleService")
    private PubVehicleService pubVehicleService;

    /**
     * 加载可用车辆列表
     * @return
     */
    @RequestMapping(value = "carList",produces = "application/json;charset=utf-8")
    public @ResponseBody String carList(@RequestParam(value = "action",required = false)String action) {
        List<PubVehicle> list =pubVehicleService.find();
        if(action != null) {
            JSONObject jo = new JSONObject();
            if(list.size()==0) {
                jo.element("result",false);
            }else {
                jo.element("result",true);
            }
            return jo.toString();
        }else {
            JSON json = JSONSerializer.toJSON(list);
            return json.toString();
        }
    }

    /**
     * 车辆分页
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @param VNum
     * @param beginDate
     * @param endDate
     * @return
     */
    @RequestMapping(value = "carController_list",produces = "application/json;charset=utf-8")
    public @ResponseBody String list (@RequestParam(value = "page")Integer page,
                        @RequestParam(value = "rows")Integer rows,
                        @RequestParam(value = "sort")String sort,
                        @RequestParam(value = "order")String order,
                        @RequestParam(value = "VNum",required = false)String VNum,
                        @RequestParam(value = "beginDate",required = false)
                            @DateTimeFormat(pattern = "yyyy-MM-dd")Date beginDate,
                        @RequestParam(value = "endDate",required = false)
                            @DateTimeFormat(pattern = "yyyy-MM-dd")Date endDate) {
        Integer pageNo = (page-1) * rows;
        Integer pageSize = rows;

        //System.out.println(page+"**"+rows+"**"+sort+"**"+order);

        Pager<PubVehicle> pager = pubVehicleService.find(pageNo,pageSize,sort,order,VNum,beginDate,endDate);
        JsonConfig jc = new JsonConfig();
        jc.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
        JSON json = JSONSerializer.toJSON(pager,jc);
        //System.out.println(json.toString());
        return json.toString();
    }

    /**
     * 跳转新增、修改车辆信息页面
     * @param VId
     * @return
     */
    @RequestMapping("carController_findById")
    public ModelAndView findById(@RequestParam(value = "VId",required = false)Integer VId) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pubmanage/car_edit");
        if(VId != null) {
            PubVehicle pubVehicle = pubVehicleService.find(VId);
            mv.addObject("pubVehicle",pubVehicle);
        }
        return mv;
    }

    /**
     * 查看车辆详情
     * @param VId
     * @return
     */
    @RequestMapping("carController_view")
    public ModelAndView view(@RequestParam(value = "VId")Integer VId) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pubmanage/car_view");

        PubVehicle pubVehicle = pubVehicleService.find(VId);
        mv.addObject("pubVehicle",pubVehicle);

        return mv;
    }

    /**
     * 新增、修改车辆信息
     * @param pubVehicle
     * @return
     */
    @RequestMapping(value = "carController_save",produces = "application/json;charset=utf-8")
    public @ResponseBody String save(PubVehicle pubVehicle) {

        Integer count = 0;
        if(pubVehicle != null && pubVehicle.getVId() != null) {
            count += pubVehicleService.modify(pubVehicle);
        }else {
            count += pubVehicleService.add(pubVehicle);
        }

        return count.toString();
    }
}
