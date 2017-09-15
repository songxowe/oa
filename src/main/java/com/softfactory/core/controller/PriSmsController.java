package com.softfactory.core.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.softfactory.core.service.PriSmsService;
import com.softfactory.core.service.PriSmsToService;
import com.softfactory.core.service.UserService;
import com.softfactory.core.util.Constants;
import com.softfactory.core.util.JsonDateValueProcessor;
import com.softfactory.core.util.Pager;
import com.softfactory.pojo.PriSms;
import com.softfactory.pojo.PriSmsTo;
import com.softfactory.pojo.User;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

/**
 * 消息处理
 */
@Controller
public class PriSmsController {

    @Resource(name = "priSmsService")
    private PriSmsService priSmsService;
    @Resource(name = "priSmsToService")
    private PriSmsToService priSmsToService;
    @Resource(name = "userService")
    private UserService userService;

    @RequestMapping(value = "priSmsList", method = RequestMethod.POST,
            produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String goList(
            @RequestParam(value = "page", required = true) Integer page,
            @RequestParam(value = "rows", required = true) Integer rows,
            @RequestParam(value = "sort", required = true) String sort,
            @RequestParam(value = "order", required = true) String order,
            @RequestParam(value = "smsType", required = false) String smsType,
            @RequestParam(value = "readFlag", required = false) Integer readFlag,
            @RequestParam(value = "fromId", required = false) Integer fromId,
            @RequestParam(value = "beginDate", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date beginDate,
            @RequestParam(value = "endDate", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            HttpServletRequest request) {

        Integer pageNo = (page - 1) * rows;
        Integer pageSize = rows;
        User user = (User) request.getSession().getAttribute(Constants.USER_IN_SESSION);
        Integer toId = user.getId();

        Pager<PriSmsTo> pager = priSmsToService.find(pageNo, pageSize, sort, order, smsType, beginDate, endDate, toId, readFlag, fromId);

        JsonConfig jc = new JsonConfig();
        jc.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd HH:mm:ss"));

        JSON json = JSONSerializer.toJSON(pager, jc);


        return json.toString();
    }

    /*
     * 查看详情
     */
    @RequestMapping("PrismsParticulars")
    public ModelAndView particulars(Integer smsId, Integer smsToId) {
        ModelAndView mv = new ModelAndView();

        //System.out.println(notifyId);
        PriSms priSms = priSmsService.findById(smsId);
        //System.out.println(notify.getCreateTime());

        User user = userService.findById(priSms.getFromId());

        priSmsToService.modifyReadFlag(smsToId);
        mv.setViewName("prisms/prisms_particulars");
        mv.addObject("priSms", priSms);
        mv.addObject("user", user);

        return mv;
    }

    /*
     * 查看已发送的信息
     */
    @RequestMapping(value = "priSmsSendList", method = RequestMethod.POST,
            produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String sendList(
            @RequestParam(value = "page", required = true) Integer page,
            @RequestParam(value = "rows", required = true) Integer rows,
            @RequestParam(value = "sort", required = true) String sort,
            @RequestParam(value = "order", required = true) String order,
            @RequestParam(value = "smsType", required = false) String smsType,
            @RequestParam(value = "readFlag", required = false) Integer readFlag,
            @RequestParam(value = "fromId", required = false) Integer fromId,
            @RequestParam(value = "toId", required = false) Integer toId,
            @RequestParam(value = "beginDate", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date beginDate,
            @RequestParam(value = "endDate", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            HttpServletRequest request) {

        Integer pageNo = (page - 1) * rows;
        Integer pageSize = rows;
        User user = (User) request.getSession().getAttribute(Constants.USER_IN_SESSION);
        fromId = user.getId();

        Pager<PriSmsTo> pager = priSmsToService.find(pageNo, pageSize, sort, order, smsType, beginDate, endDate, toId, readFlag, fromId);

        JsonConfig jc = new JsonConfig();
        jc.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd HH:mm:ss"));

        JSON json = JSONSerializer.toJSON(pager, jc);
//        System.out.println(json.toString());
//        System.out.println(readFlag);

        return json.toString();


    }

    /**
     * 发送短信
     */
    @RequestMapping("sendMessager")
    public String sendMessager(PriSms priSms, String toId, HttpServletRequest request, HttpServletResponse response) throws IOException {

        User user = (User) request.getSession().getAttribute(Constants.USER_IN_SESSION);
        Integer fromId = user.getId();
        response.setContentType("text/html;charset=utf-8");

        String arr[] = toId.split(",");
        int ids[] = new int[arr.length];
        //System.out.println(arr);
        for (int i = 0; i < arr.length; i++) {
            //System.out.println("传过来的ID：" + arr[i]);
            int id = userService.findIdByTrueName(arr[i]);
            ids[i] = id;
            //System.out.println("id:" + ids[i]);
        }

        priSms.setSmsType("个人短信");
        priSms.setFromId(fromId);
        priSms.setSendTime(new Date());
        int count = priSmsService.addByUser(priSms, ids);
        PrintWriter out = response.getWriter();
        //System.out.println(count);
        out.print(count);
        out.close();

        return null;
    }

    /*
     * 删除短信
     */
    @RequestMapping("removePriSms")
    public String removePriSms(Integer smsId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute(Constants.USER_IN_SESSION);
        Integer toId = user.getId();
        int count = priSmsToService.removePriSms(smsId, toId);
        PrintWriter out = response.getWriter();
        //System.out.println(smsId + " " + toId);
        //System.out.println(count);
        out.print(count);
        out.close();

        return null;
    }

    /*
     * 查询所有用户的真实姓名
     */
    @RequestMapping("findByAllTrueName")
    public String findByAllTrueName(HttpServletRequest request, HttpServletResponse response) throws IOException {

        User user = (User) request.getSession().getAttribute(Constants.USER_IN_SESSION);

        List<User> names = userService.findAllUser();
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).getUserTrueName().equals(user.getUserTrueName())) {
                names.remove(i);
                break;
            }
        }
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        JSONArray ja = (JSONArray) JSONSerializer.toJSON(names);
        out.print(ja);
        out.close();
        return null;
    }

    /**
     * 实时短信通知
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "priSmsPop", produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String findBySendTime(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Constants.USER_IN_SESSION);
        Integer toId = user.getId();
        Date d1 = new Date();
        long d2 = d1.getTime() - 1000;
        Date newDate = new Date(d2);

        PriSmsTo p = priSmsToService.findBySendTime(newDate, toId);
        JsonConfig jc = new JsonConfig();
        jc.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd HH:mm:ss"));
        JSON json = JSONSerializer.toJSON(p, jc);
        return json.toString();
    }
}
