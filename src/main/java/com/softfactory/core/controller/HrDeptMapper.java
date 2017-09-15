package com.softfactory.core.controller;

import com.softfactory.core.service.HrDeptService;
import com.softfactory.core.util.Pager;
import com.softfactory.pojo.HrDept;
import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 部门管理controller
 */
@Controller
public class HrDeptMapper {

    @Resource(name = "hrDeptService")
    private HrDeptService hrDeptService;

    @RequestMapping(value = "hrDeptList",produces = "application/json;charset=utf-8")
    public @ResponseBody String hrDeptList() {
        List<HrDept> list = hrDeptService.find();
        JSON json = JSONSerializer.toJSON(list);
        return json.toString();
    }

    @RequestMapping(value = "deptController",produces = "application/json;charset=utf-8")
    public @ResponseBody String list(@RequestParam(value = "page")Integer page,
                                     @RequestParam(value = "rows")Integer rows,
                                     @RequestParam(value = "sort")String sort,
                                     @RequestParam(value = "order")String order,
                                     @RequestParam(value = "deptName",required = false)String deptName) {
        Integer pageNo = (page-1) * rows;
        Integer pageSize = rows;
        Pager<HrDept> pager = hrDeptService.find(pageNo,pageSize,sort,order,deptName);
        JSON json = JSONSerializer.toJSON(pager);
        return json.toString();
    }

    /**
     * 新增、修改部门
     * @param hrDept
     * @return
     */
    @RequestMapping(value = "deptController_save",produces = "application/json;charset=utf-8")
    public @ResponseBody String save(HrDept hrDept) {

        Integer count = 0;

        if(hrDept != null && hrDept.getDeptId() != null) {
            count += hrDeptService.modify(hrDept);
        }else{
            count += hrDeptService.add(hrDept);
        }

        return count.toString();
    }
}
