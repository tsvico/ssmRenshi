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
    private Date attendanceTime;//考勤时间
    private String remark;//迟到原因备注
    private int normal_attendance;//正常出勤
    private int late;//迟到
    private int zt;//早退
    private int bj; //病假
    private int sj; //事假
    private int kg; //旷工

    //多表关联
    private User user;

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

    public int getNormal_attendance() {
        return normal_attendance;
    }

    public void setNormal_attendance(int normal_attendance) {
        this.normal_attendance = normal_attendance;
    }

    public int getLate() {
        return late;
    }

    public void setLate(int late) {
        this.late = late;
    }

    public int getZt() {
        return zt;
    }

    public void setZt(int zt) {
        this.zt = zt;
    }

    public int getBj() {
        return bj;
    }

    public void setBj(int bj) {
        this.bj = bj;
    }

    public int getSj() {
        return sj;
    }

    public void setSj(int sj) {
        this.sj = sj;
    }

    public int getKg() {
        return kg;
    }

    public void setKg(int kg) {
        this.kg = kg;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "PunchClock{" +
                "id=" + id +
                ", uid=" + uid +
                ", attendanceTime=" + attendanceTime +
                ", remark='" + remark + '\'' +
                ", normal_attendance=" + normal_attendance +
                ", late=" + late +
                ", zt=" + zt +
                ", bj=" + bj +
                ", sj=" + sj +
                ", kg=" + kg +
                ", user=" + user +
                '}';
    }
}
