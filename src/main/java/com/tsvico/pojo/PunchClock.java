package com.tsvico.pojo;

import java.util.Date;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/10/17 15:19
 * 功能 打卡model层
 */
public class PunchClock {
    private Integer id;//标识
    private Integer uid;//用户ID
    private Date punch_inTime;//打卡时间
    private Date punch_outTime;//签退时间
    private Date attendanceTime;//考勤时间
    private String remark;//迟到原因备注
    private String userip;//ip地址
    private String loginaddress;//登录地址
    private String nickname;//用户名

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Date getPunch_inTime() {
        return punch_inTime;
    }

    public void setPunch_inTime(Date punch_inTime) {
        this.punch_inTime = punch_inTime;
    }

    public Date getPunch_outTime() {
        return punch_outTime;
    }

    public void setPunch_outTime(Date punch_outTime) {
        this.punch_outTime = punch_outTime;
    }

    public Date getAttendanceTime() {
        return attendanceTime;
    }

    public void setAttendanceTime(Date attendanceTime) {
        this.attendanceTime = attendanceTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserip() {
        return userip;
    }

    public void setUserip(String userip) {
        this.userip = userip;
    }

    public String getLoginaddress() {
        return loginaddress;
    }

    public void setLoginaddress(String loginaddress) {
        this.loginaddress = loginaddress;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
