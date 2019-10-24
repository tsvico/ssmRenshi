package com.tsvico.pojo;

import java.util.Date;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/10/22 19:07
 * 功能 薪资表
 */
public class Wages {
    private int wages_id;
    private int uid;
    private Date wages_time;
    private float basic_wage; //基本工资
    private float bonus; //奖金
    private float allowance;//津贴
    private float attendance;//考勤减免

    //一对一映射关系
    private User user;

    public int getWages_id() {
        return wages_id;
    }

    public void setWages_id(int wages_id) {
        this.wages_id = wages_id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public Date getWages_time() {
        return wages_time;
    }

    public void setWages_time(Date wages_time) {
        this.wages_time = wages_time;
    }

    public float getBasic_wage() {
        return basic_wage;
    }

    public void setBasic_wage(float basic_wage) {
        this.basic_wage = basic_wage;
    }

    public float getBonus() {
        return bonus;
    }

    public void setBonus(float bonus) {
        this.bonus = bonus;
    }

    public float getAllowance() {
        return allowance;
    }

    public void setAllowance(float allowance) {
        this.allowance = allowance;
    }

    public float getAttendance() {
        return attendance;
    }

    public void setAttendance(float attendance) {
        this.attendance = attendance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Wages{" +
                "wages_id=" + wages_id +
                ", uid=" + uid +
                ", wages_time=" + wages_time +
                ", basic_wage=" + basic_wage +
                ", bonus=" + bonus +
                ", allowance=" + allowance +
                ", attendance=" + attendance +
                ", user=" + user +
                '}';
    }
}
