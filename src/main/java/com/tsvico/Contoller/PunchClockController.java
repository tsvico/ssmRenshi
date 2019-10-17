package com.tsvico.Contoller;

import com.tsvico.Service.PunchClockService;
import com.tsvico.pojo.PunchClock;
import com.tsvico.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/10/17 15:43
 * 功能
 */
@Controller
@RequestMapping(value = "admin/page/punchClock")
public class PunchClockController {

    @Autowired
    private PunchClockService punchClockService;


    @RequestMapping()
    public String punchClock(Model model) {
        List<PunchClock> p = punchClockService.findPunchClockAll(0,20);
        System.out.println(p.size());
        model.addAttribute("punchClocks",p);
        return "admin/page/punchClock";
    }

    @RequestMapping(value = "lateinfo")
    public ModelAndView lateinfo(HttpServletRequest request,
                                 HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("admin/user/lateinfo");
        return mv;
    }

    //获取打卡时间
    @RequestMapping(value = "in_time.do")
    @ResponseBody
    public String in_time(HttpSession session) throws Exception {
        String in_time = "";
        //获取当前操作的用户
        User user = (User) session.getAttribute("user");
        //user类里面只需要用户id和用户名，各位自由发挥
        PunchClock punchClock = new PunchClock();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//考勤时间格式化
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");//打卡时间格式化
        punchClock.setAttendanceTime(dateFormat.parse(dateFormat.format(date)));
        punchClock.setUid(user.getUid());
        PunchClock pc = punchClockService.if_punchin(punchClock);
        if(pc == null){
            in_time = "当前暂未打卡!";
        }else {
            in_time = format.format(pc.getPunch_inTime());
        }
        return in_time;
    }
    //获取签退时间
    @RequestMapping(value = "out_time.do")
    @ResponseBody
    public String out_time(HttpSession session) throws Exception {
        String out_time = "";
        //获取当前操作的用户
        User user = (User) session.getAttribute("user");
        PunchClock punchClock = new PunchClock();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//考勤时间格式化
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");//打卡时间格式化
        punchClock.setAttendanceTime(dateFormat.parse(dateFormat.format(date)));
        punchClock.setUid(user.getUid());
        PunchClock pc = punchClockService.if_punchout(punchClock);
        if(pc == null){
            out_time = "当前暂未签退！";
        }else {
            out_time = format.format(pc.getPunch_outTime());
        }
        return out_time;
    }

    //上班打卡
    @RequestMapping(value = "punch_in.do")
    @ResponseBody
    public int punch_in(String loginaddress, HttpServletRequest request,HttpSession session) throws Exception {
        //操作记录条数，初始化为0
        int resultTotal = 0;
        //获取用户IP地址
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip == null || ip.length() == 0 || ip.indexOf(":") > -1) {
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                ip = null;
            }
        }
        //获取当前操作的用户
        User user = (User) session.getAttribute("user");
        //打卡控制
        PunchClock punchClock = new PunchClock();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//考勤时间格式化
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");//打卡时间格式化
        Date inTime = format.parse("09:20:00");
        Date outTime = format.parse("10:00:00");
        punchClock.setPunch_inTime(format.parse(format.format(date)));
        punchClock.setUid(user.getUid());
        punchClock.setNickname(user.getUnickname());
        Date nowTime = format.parse(format.format(date));//当前时分秒
        punchClock.setAttendanceTime(dateFormat.parse(dateFormat.format(date)));
        punchClock.setUserip(ip);
        punchClock.setLoginaddress(loginaddress);
        //先查询用户是否已经打过卡
        if ( punchClockService.if_punchin(punchClock) == null) {
            if (nowTime.before(outTime) && nowTime.after(inTime)) {//迟到
                punchClockService.add_in(punchClock);
                resultTotal = -2;
            } else if (nowTime.after(outTime)) {//缺席
                resultTotal = -3;
            } else if (nowTime.before(inTime)){
                resultTotal = punchClockService.add_in(punchClock);
            }
        }else{
            resultTotal = -4; //已经打过卡了
        }
        return resultTotal;
    }

    //迟到说明情况
    @RequestMapping(value = "late.do")
    @ResponseBody
    public int late(String remark,HttpSession session) throws Exception {
        //获取当前操作的用户
        User user = (User) session.getAttribute("user");
        int resultTotal = 0;
        PunchClock punchClock = new PunchClock();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//考勤时间格式化
        punchClock.setAttendanceTime(dateFormat.parse(dateFormat.format(date)));
        punchClock.setUid(user.getUid());
        punchClock.setRemark(remark);
        resultTotal = punchClockService.late_result(punchClock);
        return resultTotal;
    }

    //下班签退
    @RequestMapping(value = "punch_out.do")
    @ResponseBody
    public int Punch_out(HttpServletRequest request,HttpSession session) throws Exception {
        //获取当前操作的用户
        User user = (User) session.getAttribute("user");
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//考勤时间格式化
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");//打卡时间格式化
        PunchClock punchClock = new PunchClock();
        punchClock.setPunch_outTime(format.parse(format.format(date)));
        Date nowTime = format.parse(format.format(date));//当前时分秒
        punchClock.setUid(user.getUid());
        punchClock.setAttendanceTime(dateFormat.parse(dateFormat.format(date)));
        int resultTotal = 0;
        Date inTime = format.parse("17:00:00");
        //防止用户重复签退
        if(punchClockService.if_punchout(punchClock) == null) {
            if (nowTime.before(inTime)) {//早退提示
                resultTotal = -2;
            } else if (nowTime.after(inTime)){
                resultTotal = punchClockService.up_out(punchClock);
            }
        }else{
            resultTotal = -3;
        }
        return resultTotal;
    }
}