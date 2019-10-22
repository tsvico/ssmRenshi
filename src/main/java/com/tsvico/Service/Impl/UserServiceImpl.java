package com.tsvico.Service.Impl;

import com.tsvico.Service.UserService;
import com.tsvico.dao.UserDao;
import com.tsvico.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/9/18 11:08
 * 功能
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUser(String username) {
        return userDao.findUserByusername(username);
    }

    @Override
    public User getUserById(int id) {
        return userDao.findUserByUid(id);
    }

    @Override
    public User checkUser(String username, String password) {
        return userDao.findUser(username,password);
    }

    @Override
    public List<User> getAll(User user) {
        return userDao.getAllUser(user);
    }

    @Override
    public List<User> getLevelAll(User user) {
        return userDao.getlevelUser(user);
    }

    @Override
    public int deleteUser(int id) {
        return userDao.delect(id);
    }

    @Override
    public int updateUser(User user) {
        return userDao.insertUser(user);
    }
}
