package com.softfactory.core.controller;

import java.util.Date;

import com.softfactory.core.util.Constants;
import com.softfactory.pojo.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softfactory.core.service.PriMailService;
import com.softfactory.core.service.PriMailToService;
import com.softfactory.core.service.UserService;
import com.softfactory.core.util.JsonDateValueProcessor;
import com.softfactory.core.util.Pager;

import com.softfactory.pojo.PriMail;
import com.softfactory.pojo.PriMailTo;


import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

//页面控制器

@Controller
public class PriMailController {

    // 首页
    @Resource(name = "priMailService")
    private PriMailService priMailService;

    @Resource(name = "priMailToService")
    private PriMailToService priMailToService;

    @Resource(name = "userService")
    private UserService userService;

    @RequestMapping("Outbox") // 页面请求的发件箱
    public void Outbox(@RequestParam(value = "fromId", required = false) String fromId,
                       @RequestParam(value = "toId", required = false) String toId,
                       @RequestParam(value = "copy", required = false) String copy,
                       @RequestParam(value = "subject", required = false) String subject,
                       @RequestParam(value = "content", required = false) String content,
                       @RequestParam(value = "name", required = false) String name,
                       @RequestParam(value = "sendFlag", required = false) String sendFlag,
                       @RequestParam(value = "smsRemind", required = false) String smsRemind,
                       @RequestParam(value = "important", required = false) String important,
                       @RequestParam(value = "mailSize", required = false) String mailSize,
                       @RequestParam(value = "deleteFlag", required = false) Integer deleteFlag,
                       @RequestParam(value = "readFlag", required = false) Integer readFlag, HttpServletResponse response) {
        PriMail PriMail = new PriMail();
        System.out.println("收件人" + fromId);
        System.out.println(fromId);
        if ("抄送".equals(copy)) {
            PriMail.setCopyTo("1");
        } else {
            PriMail.setCopyTo("0");
        }
        if ("密送".equals(copy)) {
            PriMail.setSecreatTo("1");
        } else {
            PriMail.setSecreatTo("0");
        }
        PriMail.setAttachment(name);
        PriMail.setContent(content);
        PriMail.setFromId(fromId);
        PriMail.setImportant(important);
        PriMail.setMailSize(mailSize);
        PriMail.setSubject(subject);
        PriMail.setSmsRemind(smsRemind);
        PriMail.setSendTime(new Date());
        PriMail.setSendFlag(sendFlag);

        System.out.println("toId:" + toId);

        int count = 0;
        System.out.println("_+++" + deleteFlag);
        String[] idsArray = toId.split(",");
        for (String usname : idsArray) {
            PriMail.setToId(usname);
            priMailService.add(PriMail);
            count++;
            System.out.println("count" + count);
        }

        String msg = "";

        if (sendFlag.equals("1")) {
            msg = "发送成功!";
        } else if (sendFlag.equals("0")) {
            msg = "加入草稿箱成功!";
        }

        try {
            PrintWriter out = response.getWriter();
            out.println(msg);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("modifyBy") // 页面请求的url
    public void modifyBy(@RequestParam(value = "ids", required = false) String ids,
                         @RequestParam(value = "mailToId", required = false) Integer mailToId,
                         @RequestParam(value = "sendFlag", required = false) String sendFlag,
                         @RequestParam(value = "deleteFlag", required = false) Integer deleteFlag,
                         @RequestParam(value = "readFlag", required = false) Integer readFlag, HttpServletResponse response) {
        System.out.println("sendFlag" + sendFlag);
        System.out.println("deleteFlag" + deleteFlag);
        System.out.println("readFlag" + readFlag);
        int count = 0;
        PriMailTo priMailTo = new PriMailTo();
        System.out.println(mailToId);
        if (!StringUtils.isEmpty(ids)) {
            System.out.println("id" + ids);
            System.out.println("deleteFlag_+++" + deleteFlag);
            String[] idsArray = ids.split(",");
            for (String s : idsArray) {
                System.out.println("id" + s);
                Integer id = NumberUtils.parseNumber(s, Integer.class);
                if (!StringUtils.isEmpty(deleteFlag)) {
                    priMailTo = priMailToService.findBymailToId(id);
                    priMailTo.setMailToId(id);
                    priMailTo.setDeleteFlag(deleteFlag);
                    priMailToService.modify(priMailTo);
                }
                count++;
                System.out.println("count" + count);
            }
        } else if (!StringUtils.isEmpty(readFlag)) {
            priMailTo = priMailToService.findBymailToId(mailToId);
            priMailTo.setMailToId(mailToId);
            priMailTo.setReadFlag(readFlag);
            priMailToService.modify(priMailTo);
            count = 1;
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

    @RequestMapping("modifyByTwo") // 页面请求的url
    public void modifyByTwo(@RequestParam(value = "ids", required = false) String ids,
                            @RequestParam(value = "sendFlag", required = false) String sendFlag
            , HttpServletResponse response) {
        int count = 0;
        String[] idsArray = ids.split(",");
        for (String s : idsArray) {
            System.out.println("id" + s);
            Integer id = NumberUtils.parseNumber(s, Integer.class);
            if (!StringUtils.isEmpty(sendFlag)) {
                PriMail priMail = priMailService.findById(id);
                priMail.setSendFlag(sendFlag);
                priMailService.modifyBy(priMail);
            }
            count++;
            System.out.println("count" + count);
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


    @RequestMapping("mofify") // 页面请求的url
    public void mofify(@RequestParam(value = "mailId", required = false) Integer mailId,
                       @RequestParam(value = "fromId", required = false) String fromId,
                       @RequestParam(value = "toId", required = false) String toId,
                       @RequestParam(value = "copy", required = false) String copy,
                       @RequestParam(value = "subject", required = false) String subject,
                       @RequestParam(value = "content", required = false) String content,
                       @RequestParam(value = "name", required = false) String name,
                       @RequestParam(value = "sendFlag", required = false) String sendFlag,
                       @RequestParam(value = "smsRemind", required = false) String smsRemind,
                       @RequestParam(value = "important", required = false) String important,
                       @RequestParam(value = "mailSize", required = false) String mailSize, HttpServletResponse response) {
        PriMail priMail = new PriMail();
        System.out.println("mailId" + mailId);
        if ("抄送".equals(copy)) {
            priMail.setCopyTo("抄送");
        }
        if ("密送".equals(copy)) {
            priMail.setSecreatTo("密送");
        }
        priMail.setMailId(mailId);
        priMail.setAttachment(name);
        priMail.setContent(content);
        priMail.setFromId(fromId);
        priMail.setImportant(important);
        priMail.setMailSize(mailSize);
        priMail.setToId(toId);
        priMail.setSubject(subject);
        priMail.setSmsRemind(smsRemind);
        priMail.setSendTime(new Date());
        priMail.setSendFlag(sendFlag);
        priMailService.modify(priMail);

        String msg = "";
        if (sendFlag != null && sendFlag.equals("0")) {
            msg = "加入草稿箱成功!";
        } else if (sendFlag != null && sendFlag.equals("1")) {
            msg = "发送成功!";
        }
        try {
            PrintWriter out = response.getWriter();
            out.println(msg);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // 只能软删除
    /*
	 * @RequestMapping("remove") // 页面请求的url public String
	 * remove(@RequestParam(value = "ids", required = true) String ids) { int count
	 * = 0; String[] idsArray = ids.split(","); for (String s : idsArray) { Integer
	 * mailId = NumberUtils.parseNumber(s, Integer.class); PriMail PriMail = new
	 * PriMail(); PriMail.setFromId(mailId); PriMailService.removePriMail(mailId);
	 * PriMailService.removePriMailTo(mailId); count++; } // 逻辑视图名
	 * 
	 * return null; }
	 */

    @RequestMapping("findById_email") // 页面请求的收件箱
    public ModelAndView findByMailId(Integer mailToId, Integer transmit) {
        PriMailTo priMailTo = priMailToService.findBymailToId(mailToId);
        ModelAndView mode = new ModelAndView();
        if (transmit != null && transmit == 1) {
            mode.setViewName("email/addressee_person");// 收件箱个人
        } else if (transmit != null && transmit == 2) {
            mode.setViewName("email/Ktrash_person");// 废件箱个人
        } else if (transmit != null && transmit == 3) {
        }
        // 逻辑视图名
        mode.addObject("priMailTo", priMailTo);
        return mode;
    }

    @RequestMapping("findBymailId") // 页面请求的收件箱
    public ModelAndView findBymailId(Integer mailId) {
        ModelAndView mode = new ModelAndView();
        PriMail priMail= priMailService.findById(mailId);
        mode.setViewName("email/Drafts_person");// 草稿箱个人
        mode.addObject("priMail", priMail);
        return mode;
    }

    /**
     * 未读邮件
     * @return
     */
    @RequestMapping(value = "findreadCount",produces = "application/json;charset=utf-8") // 页面请求的收件箱
    @ResponseBody
    public  String findreadCount(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(Constants.USER_IN_SESSION);
        Integer count = 0;
        count += priMailToService.findreadCount(user.getUserTrueName());
        return count.toString();
    }

    // 基本分页查询
    @RequestMapping(value = "list_email", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String list(@RequestParam(value = "page", required = false) Integer page,
                       @RequestParam(value = "rows", required = false) Integer rows,
                       @RequestParam(value = "sort", required = false, defaultValue = "toId") String sort,
                       @RequestParam(value = "order", required = false, defaultValue = "desc") String order,
                       @RequestParam(value = "fromId", required = false) String fromId,
                       @RequestParam(value = "toId", required = false) String toId,
                       @RequestParam(value = "subject", required = false) String subject,
                       @RequestParam(value = "important", required = false) String important,
                       @RequestParam(value = "sendFlag", required = false) String sendFlag,
                       @RequestParam(value = "readFlag", required = false) Integer readFlag,
                       @RequestParam(value = "deleteFlag", required = false) Integer deleteFlag,
                       @RequestParam(value = "beginDate", required = false)
                           @DateTimeFormat(pattern = "yyyy-MM-dd") Date beginDate,
                       @RequestParam(value = "endDate", required = false)
                           @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {

        System.out.println("deleteFlag" + deleteFlag + "fromId:" + fromId + "toID:" + toId + "send:" + sendFlag
                + "important：" + important + "subject:" + subject + "read:" + readFlag);
        Integer pageno = (page - 1) * rows;
        Integer pagesize = rows;


        // 3.获取分页数据
        Pager<PriMailTo> pager = priMailToService.findPager(pageno, pagesize, sort, order, fromId, toId, subject, important,
                sendFlag, readFlag, deleteFlag, beginDate, endDate);

        // 4.把分页数据转换为 json 格式的数据
        JsonConfig jc = new JsonConfig();

        jc.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd"));

        JSON json = JSONSerializer.toJSON(pager, jc);
        // 5.json数据响应给 easyui

        System.out.println(json.toString());
        return json.toString();
    }


    // 分页查询草稿箱
    @RequestMapping(value = "findDrafts", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String findDrafts(@RequestParam(value = "page", required = false) Integer page,
                             @RequestParam(value = "rows", required = false) Integer rows,
                             @RequestParam(value = "sort", required = false, defaultValue = "toId") String sort,
                             @RequestParam(value = "order", required = false, defaultValue = "desc") String order,
                             @RequestParam(value = "fromId", required = false) String fromId,
                             @RequestParam(value = "toId", required = false) String toId,
                             @RequestParam(value = "subject", required = false) String subject,
                             @RequestParam(value = "important", required = false) String important,
                             @RequestParam(value = "sendFlag", required = false) String sendFlag,
                             @RequestParam(value = "beginDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date beginDate,
                             @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {

        System.out.println("fromId:" + fromId + "toID:" + toId + "send:" + sendFlag
                + "important：" + important + "subject:" + subject);
        Integer pageno = (page - 1) * rows;
        Integer pagesize = rows;
        // 3.获取分页数据
        Pager<PriMailTo> pager = priMailToService.findDrafts(pageno, pagesize, sort, order, fromId, toId, subject, important, beginDate, endDate);

        // 4.把分页数据转换为 json 格式的数据
        JsonConfig jc = new JsonConfig();

        jc.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd"));

        JSON json = JSONSerializer.toJSON(pager, jc);
        // 5.json数据响应给 easyui

        System.out.println(json.toString());
        return json.toString();
    }


    // 基本分页查询
    @RequestMapping(value = "listTwo", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String listTwo(@RequestParam(value = "page", required = false) Integer page,
                          @RequestParam(value = "rows", required = false) Integer rows,
                          @RequestParam(value = "sort", required = false, defaultValue = "toId") String sort,
                          @RequestParam(value = "order", required = false, defaultValue = "desc") String order,
                          @RequestParam(value = "fromId", required = false) String fromId,
                          @RequestParam(value = "toId", required = false) String toId,
                          @RequestParam(value = "subject", required = false) String subject,
                          @RequestParam(value = "important", required = false) String important,
                          @RequestParam(value = "sendFlag", required = false) String sendFlag,
                          @RequestParam(value = "readFlag", required = false) Integer readFlag,
                          @RequestParam(value = "deleteFlag", required = false) Integer deleteFlag,
                          @RequestParam(value = "beginDate", required = false)
                              @DateTimeFormat(pattern = "yyyy-MM-dd") Date beginDate,
                          @RequestParam(value = "endDate", required = false)
                              @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {

        System.out.println("deleteFlag" + deleteFlag + "fromId:" + fromId + "toID:" + toId + "send:" + sendFlag
                + "important：" + important + "subject:" + subject + "read:" + readFlag);
        Integer pageno = (page - 1) * rows;
        Integer pagesize = rows;
        // 3.获取分页数据
        Pager<PriMailTo> pager = priMailToService.findPagerTwo(pageno, pagesize, sort, order, fromId, toId, subject, important,
                sendFlag, readFlag, deleteFlag, beginDate, endDate);

        // 4.把分页数据转换为 json 格式的数据
        JsonConfig jc = new JsonConfig();

        jc.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd"));

        JSON json = JSONSerializer.toJSON(pager, jc);
        // 5.json数据响应给 easyui

        System.out.println(json.toString());
        return json.toString();
    }


}
