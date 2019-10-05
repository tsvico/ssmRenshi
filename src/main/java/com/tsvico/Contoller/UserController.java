package com.tsvico.Contoller;

import com.alibaba.fastjson.JSONObject;
import com.tsvico.Service.Impl.DepartServiceImpl;
import com.tsvico.Service.Impl.PositionServiceImpl;
import com.tsvico.Service.UserService;
import com.tsvico.pojo.User;
import com.tsvico.util.CodeUtil;
import com.tsvico.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/9/18 11:11
 * 功能
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private DepartServiceImpl departService;
    @Autowired
    private PositionServiceImpl positionService;

    @Value("${salt}")
    public String salt; //获取加盐

    @Value("${Adminuser}")
    public String defaultUser;
    @Value("${Adminpassword}")
    public String defaultPassword;

    @RequestMapping("/")
    public String index(){
        User user = userService.getUser(defaultUser);
        if (user==null){
            //TODO 插入一个管理员
        }
        return "login";
    }

    @RequestMapping(value = "/getUser",produces = " text/html;charset=UTF-8")
    @ResponseBody
    public String getUser(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          HttpServletRequest request,
                          HttpSession session){
        JSONObject jsonDate = new JSONObject();
        if (!CodeUtil.checkVerifyCode(request)) {
            jsonDate.put("code",0);
            jsonDate.put("message","验证码有误");
            return jsonDate.toJSONString();
        }
        System.out.println(username+password);
        User user1 = userService.checkUser(username, MD5Utils.code(password+salt));
        if (user1!=null){
            jsonDate.put("code",1);
            jsonDate.put("message","验证通过");
            session.setAttribute("user",user1);
        }else {
            jsonDate.put("code",2);
            jsonDate.put("message","用户名或者密码错误");
        }
        return jsonDate.toJSONString();
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        if (session.getAttribute("user")!=null)
            session.removeAttribute("user");
        return "redirect:/";
    }

    @GetMapping("/admin/page/user")
    public String getUser(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        model.addAttribute("users",userService.getAll(user));
        model.addAttribute("departs",departService.getAll());
        model.addAttribute("positions",positionService.getAll());
        model.addAttribute("roles",positionService.getAllRole());
        return "admin/page/ManageUsers";
    }
    @GetMapping(value = "/admin/page/user/getUserById",produces =  "application/json;charset=UTF-8")
    @ResponseBody
    public String getUserById(int id,HttpSession session){
        User user = (User) session.getAttribute("user");
        JSONObject res = new JSONObject();
        //TODO 低级权限管理
        if (user.getRole_id()==1){
           res.put("resCode",200);
           res.put("data",userService.getUserById(id));
           return res.toJSONString();
        }
        res.put("resCode","0");
        res.put("message","权限不足");
        return res.toJSONString();
    }
    @DeleteMapping(value = "/admin/page/user/Delete",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String delete(@RequestParam("id") String ids){
        JSONObject jsonObject = new JSONObject();
        int id;
        if (ids == null){
            jsonObject.put("returnCode",0);
            jsonObject.put("mesage","请传入数据");
            return jsonObject.toJSONString();
        }
        try{
            id = Integer.parseInt(ids);
            int res = userService.deleteUser(id);
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

    @PostMapping(value = "/admin/page/user/update",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String update(User user,HttpSession session){
        System.out.println(user);
        JSONObject jsonObject = new JSONObject();
        User userd = (User) session.getAttribute("user"); //当前使用者
        if (userd.getRole_id()>user.getRole_id()){
            jsonObject.put("returnCode",0);
            jsonObject.put("message","更新失败,权限不足");
            return jsonObject.toJSONString();
        }
        User u = userService.getUserById(user.getUid());
        if (u==null){
            user.setUpassword(MD5Utils.code(user.getUpassword()+salt));
        }
        int result = userService.updateUser(user);
        if (result!=0){
            jsonObject.put("returnCode",200);
            jsonObject.put("message","更新成功");
        }else {
            jsonObject.put("returnCode",0);
            jsonObject.put("message","更新失败");
        }
        return jsonObject.toJSONString();
    }

    @RequestMapping("/admin/page/userInfo")
    public String userInfo(HttpSession session,Model model){
        User user = (User) session.getAttribute("user");
        //session存储的User没有关联表，这里需要去Dao从新取出
        model.addAttribute("user",userService.getUserById(user.getUid()));
        return "admin/page/user/userInfo";
    }
}
