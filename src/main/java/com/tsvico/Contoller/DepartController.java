package com.tsvico.Contoller;

import com.alibaba.fastjson.JSONObject;
import com.tsvico.Service.DepartService;
import com.tsvico.pojo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/9/20 10:27
 * 功能 部门管理
 */
@Controller
@RequestMapping("admin/page/depart")
public class DepartController {
    @Autowired
    private DepartService departService;

    //时间问题
    @InitBinder
    public void init(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

    /**
     * 部门管理页面 该页面地址为 admin/page/depart
     * @param model
     * @return
     */
    @RequestMapping()
    public String getDepart(Model model){
        model.addAttribute("tables",departService.getAll());
        return "admin/page/depart";
    }

    /**
     * 删除部门 接口地址 admin/page/depart/Delete
     * @param id
     * @return
     */
    @PostMapping(value = "/Delete",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String delete(String id){
        JSONObject jsonObject = new JSONObject();
        int ida;
        if (id == null){
            jsonObject.put("returnCode",0);
            jsonObject.put("mesage","请传入数据");
            return jsonObject.toJSONString();
        }
        try{
            ida = Integer.parseInt(id);
            int res = departService.deleteByOne(ida);
            if (res>0){
                jsonObject.put("returnCode",200);
                jsonObject.put("message","删除成功");
            }else {
                jsonObject.put("returnCode",0);
                jsonObject.put("message","内容不存在");
            }
        }catch (Exception e){
            jsonObject.put("returnCode",0);
            jsonObject.put("mesage","非法字符"+e.getMessage());
            return jsonObject.toJSONString();
        }
        return jsonObject.toJSONString();
    }

    /**
     * 更新或新增部门(通过设置唯一主键可以实现数据库无则新增，有则更新)
     * @param department
     * @return
     */
    @PostMapping(value = "/update",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String update(Department department){
        System.out.println(department);
        JSONObject jsonObject = new JSONObject();
        if (department.getName()==null){
            jsonObject.put("returnCode",0);
            jsonObject.put("message","更新失败");
            return jsonObject.toJSONString();
        }
        int result = departService.updateDepart(department);
        if (result!=0){
            jsonObject.put("returnCode",200);
            jsonObject.put("message","更新成功");
        }else {
            jsonObject.put("returnCode",0);
            jsonObject.put("message","更新失败");
        }
        return jsonObject.toJSONString();
    }
}
