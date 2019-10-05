package com.tsvico.Service.Impl;

import com.tsvico.Service.ConfigService;
import com.tsvico.dao.ConfigDao;
import com.tsvico.pojo.Nav;
import com.tsvico.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/9/27 13:57
 * 功能
 */
@Service
public class ConfigServiceImpl implements ConfigService {
    @Autowired
    private ConfigDao configDao;

    @Override
    public List<Nav> getMenu(User user) {
        return configDao.getAllNav();
    }

    @Override
    public List<Nav> getPermission() {
        return configDao.getAll();
    }

    @Override
    public List<Nav> getChild(int id) {
        return configDao.getChild(id);
    }

    @Override
    public Nav getOneNav(int id) {
        return configDao.getOneNav(id);
    }

    @Override
    public int DeleteNav(int id) {
        return configDao.DeleteOneNav(id);
    }

    @Override
    public int updateNav(Nav nav) {
        return configDao.insertNav(nav);
    }
}
