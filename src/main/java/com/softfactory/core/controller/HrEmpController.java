package com.softfactory.core.controller;

import com.softfactory.core.service.HrEmpService;
import com.softfactory.core.util.JsonDateValueProcessor;
import com.softfactory.core.util.JsonDeptValueProcessor;
import com.softfactory.core.util.Pager;
import com.softfactory.pojo.HrDept;
import com.softfactory.pojo.HrEmp;
import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

/**
 * 员工档案处理
 * 文件上传
 */
@Controller("hrEmpController")
public class HrEmpController {
    @Resource(name = "hrEmpService")
    private HrEmpService hrEmpService;

    /**
     * 员工档案信息分页
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @param empName
     * @param workId
     * @param deptId
     * @param beginDate
     * @param endDate
     * @param status
     * @return
     */
    @RequestMapping(value = "empList",produces = "application/json;charset=utf-8")
    public @ResponseBody String empList(@RequestParam(value = "page")Integer page,
                   @RequestParam(value = "rows")Integer rows,
                   @RequestParam(value = "sort")String sort,
                   @RequestParam(value = "order")String order,
                   @RequestParam(value = "empName",required = false)String empName,
                   @RequestParam(value = "workId",required = false)String workId,
                   @RequestParam(value = "deptId",required = false,defaultValue = "0")Integer deptId,
                   @RequestParam(value = "beginDate",required = false)
                          @DateTimeFormat(pattern = "yyyy-MM-dd")Date beginDate,
                   @RequestParam(value = "endDate",required = false)
                          @DateTimeFormat(pattern = "yyyy-MM-dd")Date endDate,
                   @RequestParam(value = "status")Integer status) {

        //System.out.println(sort + " "+page+" "+deptId);

        Integer pageNo = (page-1) * rows;
        Integer pageSize = rows;
        Pager<HrEmp> pager = hrEmpService.find(pageNo,pageSize,sort,order,empName,workId,deptId,beginDate,endDate,status);

        JsonConfig jc = new JsonConfig();
        jc.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
        jc.registerJsonValueProcessor(HrDept.class,new JsonDeptValueProcessor());
        JSON json = JSONSerializer.toJSON(pager,jc);

        return json.toString();
    }

    /**
     * 查看详情
     * @param empId
     * @return
     */
    @RequestMapping("empController_view")
    public ModelAndView view(@RequestParam(value = "empId")Integer empId) {
        ModelAndView mv = new ModelAndView();
        HrEmp hrEmp = hrEmpService.find(empId);
        mv.setViewName("hrmanage/emp_view");
        mv.addObject("hrEmp",hrEmp);
        return mv;
    }

    /**
     * 跳转新增、修改页面
     * @param empId
     * @return
     */
    @RequestMapping("empController_findById")
    public ModelAndView findById(@RequestParam(value = "empId",required = false)Integer empId) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("hrmanage/emp_edit");
        if(empId != null) {
            HrEmp hrEmp = hrEmpService.find(empId);
           mv.addObject("hrEmp",hrEmp);

        }
        return mv;
    }

    /**
     * 新增、修改档案
     * @param hrEmp
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "empController_save",produces = "application/json")
    public @ResponseBody String add(HrEmp hrEmp) throws Exception {
        Integer count = 0;
        if(hrEmp != null && hrEmp.getEmpId() != null) {
            hrEmpService.modify(hrEmp);
        }else {
            Integer maxId = hrEmpService.getLastId();
            //生成5位工号
            hrEmp.setWorkId(String.format("%05d",maxId));
            hrEmp.setStatus(1);
            hrEmpService.add(hrEmp);
        }
        count++;
        return count.toString();
    }

    /**
     * 软删除
     * @param ids
     * @return
     */
    @RequestMapping(value = "empController_remove",produces = "application/json;charset=utf-8")
    public @ResponseBody String remove(@RequestParam(required = true, value = "ids") String ids) {
        Integer count = 0;
        String[] empIds = ids.split(",");
        for (int i = 0; i < empIds.length; i++) {
            System.out.println(empIds[i]);
            Integer empId = NumberUtils.createInteger(empIds[i]);
            count += hrEmpService.updateStatus(0,empId);
        }
        return count.toString();
    }

    /**
     * 档案恢复
     * @param ids
     * @return
     */
    @RequestMapping(value = "empController_recover",produces = "application/json;charset=utf-8")
    public @ResponseBody String recover(@RequestParam(required = true, value = "ids") String ids) {
        Integer count = 0;
        String[] empIds = ids.split(",");
        for (int i = 0; i < empIds.length; i++) {
            System.out.println(empIds[i]);
            Integer empId = NumberUtils.createInteger(empIds[i]);
            count += hrEmpService.updateStatus(1,empId);
        }
        return count.toString();
    }

    /**
     * 档案永久删除
     * @param ids
     * @return
     */
    @RequestMapping(value = "empController_delete",produces = "application/json;charset=utf-8")
    public @ResponseBody String delete(@RequestParam(required = true, value = "ids") String ids) {
        Integer count = 0;
        String[] empIds = ids.split(",");
        for (int i = 0; i < empIds.length; i++) {
            System.out.println(empIds[i]);
            Integer empId = NumberUtils.createInteger(empIds[i]);
            count += hrEmpService.remove(empId);
        }
        return count.toString();
    }



    /**
     * 文件上传
     * @param request
     * @throws IOException
     */
    @RequestMapping("/upload_picture")
    @ResponseBody
    public void upload(HttpServletRequest request) throws IOException {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());

        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

            Iterator<String> iter = multiRequest.getFileNames();

            while (iter.hasNext()) {

                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    String path = request.getSession().getServletContext().getRealPath("imgupload");
                    File dir = new File(path);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }

                    file.transferTo(new File(dir, file.getOriginalFilename()));
                }
            }
        }
    }
}
