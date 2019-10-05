package com.tsvico.Contoller;

import com.alibaba.fastjson.JSONObject;
import com.tsvico.Service.ConfigService;
import com.tsvico.pojo.Nav;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/9/29 14:53
 * 功能
 */
@Controller
@RequestMapping("admin")
public class PermissionController {
    @Autowired
    private ConfigService configService;
    /**
     * 权限控制页面
     * @param model
     * @return
     */
    @GetMapping("/permission")
    public String getPermission(Model model){
        model.addAttribute("menus",configService.getPermission());
        model.addAttribute("parent",configService.getChild(0));
        return "admin/page/permission/list";
    }

    /**
     * 根据ID获取单独一行值
     * @param menu_id
     * @return
     */
    @GetMapping(value = "/permission/GetById",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getPermissionByOne(String menu_id){
        JSONObject jsonObject = new JSONObject();
        try{
            int id = Integer.parseInt(menu_id);
            Nav nav = configService.getOneNav(id);
            jsonObject.put("code",200);
            jsonObject.put("data",nav);
        } catch (NumberFormatException e) {
            jsonObject.put("code",0);
            jsonObject.put("error",e.getMessage());
        }
        return jsonObject.toJSONString();
    }

    /**
     * 权限管理页删除
     * @param ids
     * @return
     */
    @PostMapping(value = "/permission/Delete",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String delete(@RequestParam("id") String ids){
        JSONObject jsonObject = new JSONObject();
        if (ids == null){
            jsonObject.put("returnCode",0);
            jsonObject.put("mesage","请传入数据");
            return jsonObject.toJSONString();
        }
        try{
            int id = Integer.parseInt(ids);
            int res = configService.DeleteNav(id);
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

    @PostMapping(value = "/permission/update",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String update(Nav nav){
        System.out.println(nav);
        JSONObject jsonObject = new JSONObject();
        int result = configService.updateNav(nav);
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
