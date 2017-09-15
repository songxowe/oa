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


import com.softfactory.pojo.PubNotify;
import com.softfactory.pojo.PubNotifyRevice;
import com.softfactory.pojo.User;
import com.softfactory.core.service.PriSmsToService;
import com.softfactory.core.service.PubNotifyReviceService;
import com.softfactory.core.service.PubNotifyService;
import com.softfactory.core.service.UserService;
import com.softfactory.core.util.Constants;
import com.softfactory.core.util.JsonDateValueProcessor;

import com.softfactory.core.util.Pager;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

/**
 * 公告处理
 */
@Controller
public class NotifyController {

    @Resource(name = "pubNotifyService")
    private PubNotifyService pubNotifyService;
    @Resource(name = "userService")
    private UserService userService;
    @Resource(name = "pubNotifyReviceService")
    private PubNotifyReviceService pubNotifyReviceService;
    @Resource(name = "priSmsToService")
    private PriSmsToService priSmsToService;

    @RequestMapping("index")
    public String index() {
        return "index";
    }

    @RequestMapping("toadd")
    public String toadd() {
        return "notify/notify_add";
    }

    @RequestMapping(value = "notifys", method = RequestMethod.POST,
            produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String list(
            @RequestParam(value = "page", required = true) Integer page,
            @RequestParam(value = "rows", required = true) Integer rows,
            @RequestParam(value = "sort", required = true) String sort,
            @RequestParam(value = "order", required = true) String order,
            @RequestParam(value = "subject", required = false) String subject,
            @RequestParam(value = "beginDate", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date beginDate,
            @RequestParam(value = "endDate", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {

        Integer pageNo = (page - 1) * rows;
        Integer pageSize = rows;


        Pager<PubNotify> pager = pubNotifyService.find(pageNo, pageSize, sort, order, subject, beginDate, endDate);

        JsonConfig jc = new JsonConfig();
        jc.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());

        JSON json = JSONSerializer.toJSON(pager, jc);
        System.out.println(json.toString());

        return json.toString();

    }

    /*
     * 查看详情
     */
    @RequestMapping("particulars")
    public ModelAndView particulars(@RequestParam(value = "notifyId") Integer notifyId, @RequestParam(value = "reviceId", required = false) Integer reviceId) {
        ModelAndView mv = new ModelAndView();
        PubNotify notify = pubNotifyService.findById(notifyId);

        User user = userService.findById(notify.getFromId());
        if (reviceId != null) {
            pubNotifyReviceService.modifyReadFlag(reviceId);
        } else {
            pubNotifyReviceService.modifyReadFlag(notifyId, user.getId());
        }

        mv.setViewName("notify/notify_particulars");
        mv.addObject("notify", notify);
        mv.addObject("user", user);

        return mv;
    }

    /*
     * 添加新公告
     */
    @RequestMapping("add_notify")
    public String add(PubNotify notify, HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute(Constants.USER_IN_SESSION);
        notify.setCreateTime(new Date());
        notify.setNotifyStatus("正常");
        notify.setFromId(user.getId());
        int count = pubNotifyService.add(notify);
        PrintWriter out = response.getWriter();
        out.print(count);
        out.close();
        return null;
    }

    /*
     *
     * 个人用户查看公告
     */
    @RequestMapping(value = "notifysByUser", method = RequestMethod.POST,
            produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String userList(
            @RequestParam(value = "page", required = true) Integer page,
            @RequestParam(value = "rows", required = true) Integer rows,
            @RequestParam(value = "sort", required = true) String sort,
            @RequestParam(value = "order", required = true) String order,
            @RequestParam(value = "subject", required = false) String subject,
            @RequestParam(value = "beginDate", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date beginDate,
            @RequestParam(value = "endDate", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam(value = "notifyId", required = false) Integer notifyId,
            HttpServletRequest request) {

        Integer pageNo = (page - 1) * rows;
        Integer pageSize = rows;
        User user = (User) request.getSession().getAttribute(Constants.USER_IN_SESSION);
        Integer toId = user.getId();


        Pager<PubNotifyRevice> pager = pubNotifyReviceService.find(pageNo, pageSize, sort, order, subject, notifyId, beginDate, endDate, toId);


        JsonConfig jc = new JsonConfig();
        jc.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());

        JSON json = JSONSerializer.toJSON(pager, jc);
        System.out.println(json.toString());

        return json.toString();

    }

    /*
     * 首页跳转公告页面
     */
    @RequestMapping("goNotifyUser")
    public String goNotifyUser() {

        return "notify/notify_findAll";
    }

    /*
     * 删除公告
     */
    @RequestMapping("delete")
    public String delete(Integer notifyId, HttpServletResponse response) throws IOException {
        int count = pubNotifyService.remove(notifyId);
        PrintWriter out = response.getWriter();
        out.print(count);
        out.close();
        return null;
    }

    /*
     * 查看前五条公告
     */
    @RequestMapping(value = "notifyList", produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String findList(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Constants.USER_IN_SESSION);

        Integer toId = user.getId();

        List<PubNotifyRevice> revices = pubNotifyReviceService.findList(toId);

        JsonConfig jc = new JsonConfig();
        jc.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd HH:mm:ss"));
        JSONArray json = (JSONArray) JSONSerializer.toJSON(revices, jc);

        return json.toString();
    }

    /*
     * 首页显示未读短信条数
     */
    @RequestMapping("smsFindReadFlag")
    public String findByReadFlagSms(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute(Constants.USER_IN_SESSION);

        Integer toId = user.getId();

        int count = priSmsToService.findByReadFlag(toId);

        PrintWriter out = response.getWriter();
        out.print(count);
        out.close();
        return null;
    }


}
