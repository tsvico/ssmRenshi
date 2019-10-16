package com.tsvico.dao;

import com.tsvico.pojo.Department;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/9/20 17:38
 * 功能
 */
@Repository
public interface DepartDao {

    @Select("select * from department")
    List<Department> getAllDepart();

    @Select("select * from department where dept_id=#{id}")
    Department selectDeptById(int id);

    @Delete("DELETE FROM `department` WHERE `dept_id`=#{id,jdbcType=INTEGER}")
    int deleteByOne(int id);

    //此处表ID要增加Unique索引 参考语句 ALTER TABLE `TableName` ADD unique(`id`);
    //存在则更新,影响记录条数2   不存在则插入,影响记录条数1
    @Insert("insert INTO department" +
            "(dept_id, name, telephone, email,address,dCreateDate) " +
            "VALUES (#{dept_id},#{name},#{telephone},#{email},#{address},#{dCreateDate}) " +
            "ON DUPLICATE KEY " +
            "UPDATE " +
            "name = #{name},telephone = #{telephone},email = #{email},address = #{address},dCreateDate=#{dCreateDate}")
    Integer updateByOne(Department department);
}
