package com.tsvico.Service;

import com.tsvico.pojo.User;

import java.util.List;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/9/18 11:07
 * 功能
 */
public interface UserService {
    User getUser(String username);
    User getUserById(int id);
    User checkUser(String username, String password);
    List<User> getAll(User user);
    List<User> getLevelAll(User user);
    int deleteUser(int id);
    int updateUser(User user);
}
