package com.softfactory.core.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.softfactory.core.service.PubVoteService;
import com.softfactory.core.util.Constants;
import com.softfactory.core.util.JsonDateValueProcessor;
import com.softfactory.core.util.JsonUserValueProcessor;
import com.softfactory.core.util.Pager;
import com.softfactory.pojo.PubVote;
import com.softfactory.pojo.User;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

/**
 * 投票
 */
@Controller
public class PubVoteController {

    @Resource(name = "pubVoteService")
    private PubVoteService pubVoteService;

    @RequestMapping(value = "goVote")
    public String goVote() {
        //System.out.println("进入goVote方法");

        return "redirect:vote/indexVoteList.jsp?voteStatus=2";
    }

    @RequestMapping("goEcharts")
    public String getEcharts(Integer voteId) {
        return "redirect:vote/indexList6.jsp?voteId="+voteId;
    }

    /**
     * 修改投票状态
     * @param voteId
     * @return
     */
    @RequestMapping(value = "updateVote", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String updateVote(Integer voteId) {
        PubVote pubVote = pubVoteService.selectByPrimaryKey(voteId);
        pubVote.setVoteStatus("3");
        int count = pubVoteService.updateByPrimaryKey(pubVote);
        JSONObject json = new JSONObject();
        json.element("count", count);

        return json.toString();
    }

    /**
     * 查询单个投票信息
     * @param voteId
     * @return
     */
    @RequestMapping(value = "findById")
    public ModelAndView findById(Integer voteId) {
        ModelAndView mv = new ModelAndView();
        PubVote pVote = pubVoteService.selectByPrimaryKey(voteId);
        //System.out.println(pVote);
        if (pVote != null) {
            mv.addObject("pubVote", pVote);
            mv.setViewName("vote/showVote");
        } else {
            mv.setViewName("redirect:vote/voteList");
        }
        return mv;
    }

    /**
     * 主页显示五个投票信息
     * @return
     */
    @RequestMapping(value = "votes", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String findPager() {
        Integer page = 0;
        Integer rows = 5;
        String sort = "create_time";
        String order = "desc";
        String voteStatus = "2";
        List<PubVote> pager = pubVoteService.findPageBefore(page, rows, sort, order, voteStatus, null, null, null);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[]{"subs"});
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd HH:mm:ss"));
        jsonConfig.registerJsonValueProcessor(User.class, new JsonUserValueProcessor());

        JSONObject json = new JSONObject();
        json.element("votes", pager, jsonConfig);

        //System.out.println(json);

        return json.toString();
    }

    /**
     * 投票信息分页
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @param voteStatus
     * @param subject
     * @param beginDate
     * @param endDate
     * @return
     */
    @RequestMapping(value = "pubVotes", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String findByPager(Integer page, Integer rows, String sort, String order, @RequestParam(value = "voteStatus") String voteStatus, @RequestParam(value = "subject", required = false) String subject, @RequestParam(value = "beginDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date beginDate, @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endDate) {
        //System.out.println("状态: "+voteStatus);
        Pager<PubVote> pager = pubVoteService.findPage(page, rows, sort, order, voteStatus, subject, beginDate, endDate);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[]{"subs"});
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd HH:mm:ss"));
        JSON json = JSONSerializer.toJSON(pager, jsonConfig);
        //System.out.println(json);
        return json.toString();
    }

    @RequestMapping(value = "/pubVote", produces = "application/json")
    public @ResponseBody
    String add(PubVote pubVote, @RequestParam("votename") String votename, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User uesr = (User) session.getAttribute(Constants.USER_IN_SESSION);

        Integer count = 0;
        pubVote.setCreateTime(new Date());
        pubVote.setFromId(uesr.getId());
        pubVote.setCanView("1");
        pubVote.setVoteStatus("2");
        //System.out.println(pubVote);
        //System.out.println(votename);
        String ids[] = votename.split(",");
        count = pubVoteService.add(pubVote, ids);

        return count.toString();
    }
}
