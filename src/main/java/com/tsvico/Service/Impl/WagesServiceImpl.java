package com.tsvico.Service.Impl;

import com.tsvico.Service.WagesService;
import com.tsvico.dao.WagesDao;
import com.tsvico.pojo.User;
import com.tsvico.pojo.Wages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/10/23 9:07
 * 功能
 */
@Service
public class WagesServiceImpl implements WagesService {

    @Autowired
    private WagesDao wagesDao;

    @Override
    public List<Wages> getAll(User user) {
        return wagesDao.getAllWages(user);
    }

    @Override
    public Wages getWagesById(int id) {
        return wagesDao.getWagesById(id);
    }

    @Override
    public List<Wages> findWageByname(String name) {
        return wagesDao.findWageByname(name);
    }

    @Override
    public int deleteWageById(int id) {
        return wagesDao.deleteWageById(id);
    }

    @Override
    public int updateWage(Wages wages) {
        return wagesDao.updateWage(wages);
    }
}
