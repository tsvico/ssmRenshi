package com.tsvico.Service;

import com.tsvico.pojo.Nav;
import com.tsvico.pojo.User;

import java.util.List;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/9/27 13:56
 * 功能
 */
public interface ConfigService {
    List<Nav> getMenu(User user);
    List<Nav> getPermission();
    List<Nav> getChild(int id);
    Nav getOneNav(int id);
    int DeleteNav(int id);
    int updateNav(Nav nav);
}
