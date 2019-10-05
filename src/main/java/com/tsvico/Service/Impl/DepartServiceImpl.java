package com.tsvico.Service.Impl;

import com.tsvico.Service.DepartService;
import com.tsvico.dao.DepartDao;
import com.tsvico.pojo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/9/20 17:45
 * 功能
 */
@Service
public class DepartServiceImpl implements DepartService {

    @Autowired
    private DepartDao departDao;

    //获取全部
    @Override
    public List<Department> getAll() {
        return departDao.getAllDepart();
    }

    //删除一个
    @Override
    public int deleteByOne(int id) {
        return departDao.deleteByOne(id);
    }

    @Override
    public int updateDepart(Department department) {
        return departDao.updateByOne(department);
    }
}
