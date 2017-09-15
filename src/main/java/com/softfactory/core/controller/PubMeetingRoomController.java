package com.softfactory.core.controller;

import com.softfactory.core.service.PubMeetingRoomService;
import com.softfactory.core.util.Pager;
import com.softfactory.pojo.PubMeetingRoom;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class PubMeetingRoomController {

    @Resource(name = "pubMeetingRoomService")
    private PubMeetingRoomService pubMeetingRoomService;

    @RequestMapping(value = "meetingRoomList",produces = "application/json;charset=utf-8")
    public @ResponseBody String meetingRoomList(@RequestParam(value = "action",required = false)String action) {

        List<PubMeetingRoom> list = pubMeetingRoomService.find();

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
     * 分页
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @param mrName
     * @return
     */
    @RequestMapping(value = "roomController_list",produces = "application/json;charset=utf-8")
    public @ResponseBody String list(@RequestParam(value = "page")Integer page,
                       @RequestParam(value = "rows")Integer rows,
                       @RequestParam(value = "sort")String sort,
                       @RequestParam(value = "order")String order,
                       @RequestParam(value = "mrName",required = false)String mrName) {

        Integer pageNo = (page-1) * rows;
        Integer pageSize = rows;
        Pager<PubMeetingRoom> pager = pubMeetingRoomService.find(pageNo,pageSize,sort,order,mrName);

        JSON json = JSONSerializer.toJSON(pager);

        return json.toString();
    }

    @RequestMapping("roomController_findById")
    public ModelAndView findById(@RequestParam(value = "mrId",required = false)Integer mrId) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pubmanage/meetingroom_edit");
        if(mrId != null) {
            PubMeetingRoom pubMeetingRoom = pubMeetingRoomService.find(mrId);
            mv.addObject("pubMeetingRoom",pubMeetingRoom);
        }
        return mv;
    }

    /**
     * 详情
     * @param mrId
     * @return
     */
    @RequestMapping("roomController_view")
    public ModelAndView view(@RequestParam(value = "mrId")Integer mrId) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pubmanage/meetingroom_view");

        PubMeetingRoom pubMeetingRoom = pubMeetingRoomService.find(mrId);
        mv.addObject("pubMeetingRoom",pubMeetingRoom);

        return mv;
    }

    /**
     * 新增、修改会议室
     * @param pubMeetingRoom
     * @return
     */
    @RequestMapping(value = "roomController_save",produces = "application/json;charset=utf-8")
    public @ResponseBody String save(PubMeetingRoom pubMeetingRoom) {

        Integer count = 0;
        if(pubMeetingRoom != null && pubMeetingRoom.getMrId() != null) {
            count += pubMeetingRoomService.modify(pubMeetingRoom);
        }else {
            count += pubMeetingRoomService.add(pubMeetingRoom);
        }

        return count.toString();
    }
}
