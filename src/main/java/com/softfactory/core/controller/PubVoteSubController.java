package com.softfactory.core.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softfactory.core.service.PubVoteService;
import com.softfactory.core.service.PubVoteSubService;
import com.softfactory.core.util.Constants;
import com.softfactory.core.util.JsonDateValueProcessor;
import com.softfactory.pojo.PubVote;
import com.softfactory.pojo.PubVoteItem;
import com.softfactory.pojo.PubVoteSub;
import com.softfactory.pojo.User;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
public class PubVoteSubController {

    @Resource(name = "pubVoteSubService")
    private PubVoteSubService pubVoteSubService;
    @Resource(name = "pubVoteService")
    private PubVoteService pubVoteService;

    /**
     * 显示图表信息
     * @param voteId
     * @param request
     * @return
     */
    @RequestMapping(value = "list", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String list(@RequestParam(value = "voteId") Integer voteId, HttpServletRequest request) {
        //System.out.println("选择的投票标题ID: "+voteId);

        PubVote pv = pubVoteService.selectByPrimaryKey(voteId);

        HttpSession session = request.getSession();
        User uesr = (User) session.getAttribute(Constants.USER_IN_SESSION);

        List<PubVoteSub> list = pubVoteSubService.findByVoteId(voteId);
        JSONObject json = new JSONObject();

        //查询是否已经投票
        PubVoteItem pubVoteItem = pubVoteSubService.findById(voteId, uesr.getId());

        json.element("json", list);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        json.element("pv", pv, jsonConfig);

        //mark: 3表示已经终止,2表示已经投票,1表示还没有投票
        if ("3".equals(pv.getVoteStatus())) {
            json.element("mark", 3);
        } else if (pubVoteItem != null) {
            json.element("mark", 2);
        } else {
//			json.accumulate("json", list);
//			json.element("pv", pv);
            json.element("mark", 1);
        }
        //System.out.println(json);

        return json.toString();
    }

    /**
     * 完成投票
     * @param subId
     * @param request
     * @return
     */
    @RequestMapping(value = "update", produces = "application/json")
    public @ResponseBody
    String update(Integer subId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User uesr = (User) session.getAttribute(Constants.USER_IN_SESSION);

        //System.out.println("选择的id: "+subId);
        PubVoteSub pvs = pubVoteSubService.selectByPrimaryKey(subId);
        pvs.setVoteCount(pvs.getVoteCount() + 1);
        PubVoteItem pubVoteItem = new PubVoteItem();
        pubVoteItem.setUserId(uesr.getId());
        pubVoteItem.setSubId(subId);
        Integer rows = pubVoteSubService.updateByPrimaryKey(pvs, pubVoteItem);
        //System.out.println("修改的数量: "+rows);
        return rows.toString();
    }
}
