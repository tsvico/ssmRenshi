package com.tsvico.Contoller;

import com.alibaba.fastjson.JSONObject;
import com.tsvico.Service.WagesService;
import com.tsvico.dao.UserDao;
import com.tsvico.pojo.User;
import com.tsvico.pojo.Wages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/10/23 10:36
 * 功能
 */
@Controller
@RequestMapping("/admin/page/wages")
public class WagesController {
    @Autowired
    private WagesService wagesService;

    @Autowired
    private UserDao userDao;

    //时间问题
    @InitBinder
    public void init(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

    @GetMapping
    public String AllWages(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        model.addAttribute("wages",wagesService.getAll(user));
        if (user.getRole_id()==1)
            model.addAttribute("users",userDao.getAllUser(user));
        else
            model.addAttribute("users",user);
        return "admin/page/wages";
    }

    @GetMapping(value = "findByname",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String findByname(String page,String limit,String name){
        System.out.println(name);
        List<Wages> wages = wagesService.findWageByname(name);
        JSONObject js = new JSONObject();
        js.put("code",0);
        js.put("msg"," ");
        js.put("count",wages.size());
        js.put("data",wages);
        return js.toJSONString();
    }
    @PostMapping(value = "/Delete",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String delete(@RequestParam("wages_id") String ids){
        JSONObject jsonObject = new JSONObject();
        int id;
        if (ids == null){
            jsonObject.put("returnCode",0);
            jsonObject.put("mesage","请传入数据");
            return jsonObject.toJSONString();
        }
        try{
            id = Integer.parseInt(ids);
            int res = wagesService.deleteWageById(id);
            if (res>0){
                jsonObject.put("returnCode",200);
                jsonObject.put("message","删除成功");
                jsonObject.put("data","delete"+id);
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

    @PostMapping(value = "/update",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String update(Wages wages,HttpSession session){
        JSONObject jsonObject = new JSONObject();
        User userd = (User) session.getAttribute("user"); //当前使用者
        if (userd.getRole_id()>1){
            jsonObject.put("returnCode",0);
            jsonObject.put("message","更新失败,权限不足");
            return jsonObject.toJSONString();
        }
        try {
            int result = wagesService.updateWage(wages);
            if (result==2){
                jsonObject.put("returnCode",200);
                jsonObject.put("message","更新成功");
            }else if (result==1){
                jsonObject.put("returnCode",200);
                jsonObject.put("message","插入成功");
            }else {
                jsonObject.put("returnCode",0);
                jsonObject.put("message","更新失败");
            }
        }catch (Exception e){
            jsonObject.put("returnCode",0);
            jsonObject.put("message","系统错误");
        }
        return jsonObject.toJSONString();
    }
}
