package com.softfactory.core.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softfactory.core.util.Constants;
import com.softfactory.pojo.User;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softfactory.core.service.DaliyService;
import com.softfactory.core.util.JsonDateValueProcessor;
import com.softfactory.core.util.Pager;
import com.softfactory.pojo.Daliy;


import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

/**
 * 个人日志controller
 */
@Controller
public class DaliyController {
    @Resource(name = "daliyService")
    private DaliyService daliyService;

    @RequestMapping(value = "daliyContronller", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String list(
            @RequestParam(required = true, value = "page") Integer page,
            @RequestParam(required = true, value = "rows") Integer rows,
            @RequestParam(required = true, value = "sort") String sort,
            @RequestParam(required = true, value = "order") String order,
            @RequestParam(required = false, value = "classmate") String classmate,
            @RequestParam(required = false, value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam(required = false, value = "beginDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date beginDate,
            HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute(Constants.USER_IN_SESSION);

        Integer pageno = (page - 1) * rows;
        Integer pagesize = rows;

        Pager<Daliy> pager = daliyService.findPager(pageno, pagesize, sort, order, classmate, beginDate, endDate, user.getUserTrueName());
        for (Daliy daliy : pager.getRows()) {
            System.out.println(daliy.getLogDate());
        }
        JsonConfig jc = new JsonConfig();

        jc.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        JSON json = JSONSerializer.toJSON(pager, jc);
        System.out.println(json.toString());
        return json.toString();


    }

    @RequestMapping("/DaliyController_remove")
    public void remove(@RequestParam(required = true, value = "ids") String ids, HttpServletResponse response) {
        int count = 0;
        String[] daliys = ids.split(",");
        for (int i = 0; i < daliys.length; i++) {
            Integer id = NumberUtils.createInteger(daliys[i]);
            count += daliyService.remove(id);
        }

        try {
            PrintWriter out = response.getWriter();
            out.println(count);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "DaliyController_add", produces = "application/json", method = RequestMethod.GET)
    public @ResponseBody
    String add(@RequestParam(value = "username", required = false) String username,
               @RequestParam(value = "classmate", required = false, defaultValue = "0") String classmate,
               @RequestParam(value = "method", required = false, defaultValue = "0") String method,
               @RequestParam(value = "msg", required = false, defaultValue = "0") String msg,
               @RequestParam(value = "priopity", required = false, defaultValue = "0") String priopity,
               @RequestParam(value = "logDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date logDate,
               HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Constants.USER_IN_SESSION);
        //String name=requset.getParamenter("name");
        System.out.println(username + " ==  " + classmate + " .." + "" + method + "" + msg + "" + priopity + "" + logDate);
        Daliy daliys = new Daliy();
        daliys.setClassmate(classmate);
        daliys.setLogDate(new Date());
        daliys.setMethod(method);
        daliys.setMsg(msg);
        daliys.setPriopity(priopity);
        daliys.setUsername(user.getUserTrueName());
        Integer ids = daliyService.add(daliys);
        return ids.toString();
    }

	/*  @RequestMapping("/daliyController_view")
	  public String view(@RequestParam(required = true, value = "id") Integer id, ModelMap modelMap) {
	    if (id != null) {
	      Daliy daliys = daliyService.findById(id);
	      modelMap.put("daliys", daliys);
	    }
	    return "daliyview";
	  }*/


    @RequestMapping(value = "DaliyController_modify", produces = "application/json", method = RequestMethod.GET)
    public @ResponseBody
    String modify(@RequestParam(value = "id", required = true) Integer id,
                  @RequestParam(value = "username", required = false) String username,
                  @RequestParam(value = "classmate", required = false, defaultValue = "0") String classmate,
                  @RequestParam(value = "method", required = false, defaultValue = "0") String method,
                  @RequestParam(value = "msg", required = false, defaultValue = "0") String msg,
                  @RequestParam(value = "priopity", required = false, defaultValue = "0") String priopity,
                  @RequestParam(value = "logDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date logDate) {

        //String name=requset.getParamenter("name");
        System.out.println(id + username + " ==  " + classmate + " .." + "" + method + "" + msg + "" + priopity + "" + logDate);
        Daliy daliys = new Daliy();
        daliys.setId(id);
        daliys.setClassmate(classmate);
        daliys.setLogDate(logDate);
        daliys.setMethod(method);
        daliys.setMsg(msg);
        daliys.setPriopity(priopity);
        daliys.setUsername(username);
        Integer ids = daliyService.modify(daliys);
        return ids.toString();
    }

}

	
