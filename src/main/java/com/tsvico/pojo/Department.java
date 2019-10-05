package com.tsvico.pojo;

import java.util.Date;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/9/20 17:38
 * 功能 部门类
 */
public class Department {
    private int dept_id;
    private String name;
    private String telephone;
    private String email;
    private String address;
    //private int supdepartment; //上级部门
    //private String description;//部门描述
    private Date dCreateDate; //部门创建日期

    public int getDept_id() {
        return dept_id;
    }

    public void setDept_id(int dept_id) {
        this.dept_id = dept_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getdCreateDate() {
        return dCreateDate;
    }

    public void setdCreateDate(Date dCreateDate) {
        this.dCreateDate = dCreateDate;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + dept_id +
                ", name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", establishmentdate=" + dCreateDate +
                '}';
    }
}
