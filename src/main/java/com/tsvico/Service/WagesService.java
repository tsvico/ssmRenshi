package com.tsvico.Service;

import com.tsvico.pojo.User;
import com.tsvico.pojo.Wages;

import java.util.List;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/10/22 19:19
 * 功能
 */
public interface WagesService {
    List<Wages> getAll(User user);
    Wages getWagesById(int id);
    List<Wages> findWageByname(String name);
    int deleteWageById(int id);
    int updateWage(Wages wages);
}
