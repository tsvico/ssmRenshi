package com.tsvico.dao;

import com.tsvico.pojo.Position;
import com.tsvico.pojo.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/9/23 16:35
 * 功能 职位管理
 */
@Repository
public interface PositionDao {
    @Select("select * from position") //查询所属职位
    List<Position> getAllPosition();

    @Select("select * from role")//查询职位类别
    List<Role> getAllRole();

    @Select("select * from position where position_id = #{id}") //查询所属职位
    Position selectPositionByid(int id);

    @Delete("DELETE FROM `position` WHERE `position_id`=#{id,jdbcType=INTEGER}")
    int deleteByOne(int id);

    //此处表ID要增加Unique索引 参考语句 ALTER TABLE `TableName` ADD unique(`id`);
    //存在则更新,影响记录条数2   不存在则插入,影响记录条数1
    @Insert("insert INTO `position`" +
            "(`position_id`, `level`, `name`, `describtion`) " +
            "VALUES (#{position_id},#{level},#{name},#{describtion}) " +
            "ON DUPLICATE KEY " +
            "UPDATE " +
            "`level` = #{level},`name` = #{name},`describtion` = #{describtion}")
    Integer updateByOne(Position position);
}
