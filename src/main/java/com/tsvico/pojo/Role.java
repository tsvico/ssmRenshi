package com.tsvico.pojo;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/9/24 13:46
 * 功能 职位类别 略分
 */

/**
 * CREATE TABLE `aoa_role_` (
 *   `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
 *   `role_name` varchar(255) DEFAULT NULL,
 *   `role_value` int(11) DEFAULT NULL,
 *   PRIMARY KEY (`role_id`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4;
 */
public class Role {
    private int role_id;
    private String role_name;
    private int role_value;

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public int getRole_value() {
        return role_value;
    }

    public void setRole_value(int role_value) {
        this.role_value = role_value;
    }

    @Override
    public String toString() {
        return "Role{" +
                "role_id=" + role_id +
                ", role_name='" + role_name + '\'' +
                ", role_value=" + role_value +
                '}';
    }
}
