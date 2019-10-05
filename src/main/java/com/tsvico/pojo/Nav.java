package com.tsvico.pojo;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/9/20 10:47
 * 功能 侧边栏以及顶栏数据Bean
 */
public class Nav {
    private int menu_id;
    private int is_show;
    @JSONField(name = "title")
    private String menu_name;
    @JSONField(name = "icon")
    private String menu_icon;
    @JSONField(name = "href")
    private String menu_url;
    @JSONField(name = "spread")
    private boolean spread = false;
    @JSONField(name = "children")
    private List<Nav> children;
    private int parent_id;
    private int role_id;

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public int getIs_show() {
        return is_show;
    }

    public void setIs_show(int is_show) {
        this.is_show = is_show;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getMenu_icon() {
        return menu_icon;
    }

    public void setMenu_icon(String menu_icon) {
        this.menu_icon = menu_icon;
    }

    public String getMenu_url() {
        return menu_url;
    }

    public void setMenu_url(String menu_url) {
        this.menu_url = menu_url;
    }

    public boolean isSpread() {
        return spread;
    }

    public void setSpread(boolean spread) {
        this.spread = spread;
    }

    public List<Nav> getChildren() {
        return children;
    }

    public void setChildren(List<Nav> children) {
        this.children = children;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    @Override
    public String toString() {
        return "Nav{" +
                "menu_id=" + menu_id +
                ", is_show=" + is_show +
                ", menu_name='" + menu_name + '\'' +
                ", menu_icon='" + menu_icon + '\'' +
                ", menu_url='" + menu_url + '\'' +
                ", spread=" + spread +
                ", children=" + children +
                ", role_id='" + role_id + '\'' +
                '}';
    }
}
