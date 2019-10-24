package com.tsvico.dao;

import com.tsvico.pojo.Wages;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/10/22 19:11
 * 功能  薪资
 * 这里使用xml文件的方式配置，熟悉两种方法的使用，体验注解与xml配置的异同点
 */
public interface WagesDao {

    List<Wages> getAllWages();

    Wages getWagesById(int id);

    List<Wages> findWageByname(String name);

    int deleteWageById(@Param("wages_id") int wages_id);

    int updateWage(Wages wages);

}
