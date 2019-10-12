package com.tsvico.pojo;

import java.util.Date;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/9/11 11:20
 */
public class User {
    private int uid;
    private String uname;
    private String upassword;
    private String unickname;
    private int uphone;
    private Date ubirthday;
    private int uage;
    private String avater;
    private String email;
    //外键
    //private int dept_id;
    //private int position_id;
    private Department depart;
    private Position position;
    private int role_id;
    //private Role role;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public String getUnickname() {
        return unickname;
    }

    public void setUnickname(String unickname) {
        this.unickname = unickname;
    }

    public int getUphone() {
        return uphone;
    }

    public void setUphone(int uphone) {
        this.uphone = uphone;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public int getUage() {
        return uage;
    }

    public void setUage(int uage) {
        this.uage = uage;
    }

    public String getAvater() {
        return avater;
    }

    public Date getUbirthday() {
        return ubirthday;
    }

    public void setUbirthday(Date ubirthday) {
        this.ubirthday = ubirthday;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDepart() {
        return depart;
    }

    public void setDepart(Department depart) {
        this.depart = depart;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", upassword='" + upassword + '\'' +
                ", unickname='" + unickname + '\'' +
                ", uphone=" + uphone +
                ", ubirthday=" + ubirthday +
                ", uage=" + uage +
                ", avater='" + avater + '\'' +
                ", email='" + email + '\'' +
                ", depart=" + depart +
                ", position=" + position +
                ", role_id=" + role_id +
                '}';
    }
}
