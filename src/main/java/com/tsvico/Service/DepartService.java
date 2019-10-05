package com.tsvico.Service;

import com.tsvico.pojo.Department;

import java.util.List;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/9/20 17:44
 * 功能
 */
public interface DepartService {
    List<Department> getAll();
    int deleteByOne(int id);
    int updateDepart(Department department);
}
