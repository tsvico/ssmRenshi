package com.tsvico.Contoller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tsvico.Service.ConfigService;
import com.tsvico.pojo.Nav;
import com.tsvico.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/9/19 21:34
 * 功能
 */
@Controller
@RequestMapping("admin")
public class indexController {

    @Autowired
    private ConfigService configService;

    /**
     * 后台首页
     * @param session
     * @param model
     * @return
     */
    @RequestMapping
    public String adminIndex(HttpSession session,
                             Model model){
        if (session.getAttribute("user")!=null){
            User user = (User)session.getAttribute("user");
            model.addAttribute("user",user);
        }else {
            return "redirect:/";
        }
        return "admin/index";
    }

    /**
     * 根据用户权限获取侧边栏内容
     * @param session
     * @return
     */
    @GetMapping(value = "/navs",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String navs(HttpSession session){
        User user = (User) session.getAttribute("user");
        List<Nav> listNav = configService.getMenu(user);
        System.out.println(listNav);
        session.setAttribute("menus",listNav);
        /*List<Nav> listNav = new ArrayList<>();
        List<Nav> listChild1 = new ArrayList<>();
        listChild1.add(new Nav("部门管理","layui-icon-radio","admin/page/depart",false,null));
        listChild1.add(new Nav("职位管理","layui-icon-radio","admin/page/position",false,null));
        listNav.add(new Nav("用户管理","layui-icon-username","#",false,listChild1));
        List<Nav> listChild2 = new ArrayList<>();
        listChild2.add(new Nav("考勤管理","layui-icon-radio","admin/page/depart",false,null));
        listChild2.add(new Nav("考勤周报表","layui-icon-radio","admin/page/depart",false,null));
        listNav.add(new Nav("考勤管理","layui-icon-log","page/img/images.html",false,listChild2));
        */
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("contentManagement",JSON.toJSON(listNav));
        return jsonObject.toJSONString();
    }

}
