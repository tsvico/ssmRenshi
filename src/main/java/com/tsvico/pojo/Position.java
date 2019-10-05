package com.tsvico.pojo;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/9/23 16:31
 * 功能 职位
 */

/**
 * CREATE TABLE `position` (
 *   `position_id` bigint(20) NOT NULL AUTO_INCREMENT,
 *   `level` int(11) DEFAULT NULL,
 *   `name` varchar(255) DEFAULT NULL,
 *   `describtion` varchar(255) DEFAULT NULL,
 *   PRIMARY KEY (`position_id`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4;
 */
public class Position {
    private int position_id;
    private int level;
    private String name;
    private String describtion;

    public int getPosition_id() {
        return position_id;
    }

    public void setPosition_id(int position_id) {
        this.position_id = position_id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }

    @Override
    public String toString() {
        return "Position{" +
                "position_id=" + position_id +
                ", level=" + level +
                ", name='" + name + '\'' +
                ", describtion='" + describtion + '\'' +
                '}';
    }
}
