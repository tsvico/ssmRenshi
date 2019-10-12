package com.tsvico.dao;

import com.tsvico.pojo.Nav;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/9/27 13:35
 * 功能
 */
@Repository
public interface ConfigDao {

    /**
     *
     * @return
     */
    @Select("select * from menu where parent_id = 0 and is_show = 1")
    @Results({
            @Result(id=true,column="menu_id",property="menu_id"), //列和属性相同可以省略???
            @Result(column="is_show",property="is_show"),
            @Result(column="menu_icon",property="menu_icon"),
            @Result(column="menu_url",property="menu_url"),
            @Result(column = "menu_name",property = "menu_name"),
            @Result(column = "role_id",property = "role_id"),
            @Result(column="menu_id",property="children",
                    many = @Many(
                            select = "getChild",
                            fetchType= FetchType.EAGER)
            )
    })
    List<Nav> getAllNav(); //TODO 要传入user的

    @Select("select * from menu where parent_id = #{parent_id} and is_show = 1")
    List<Nav> getChild(int parent_id);

    @Select("select * from menu")
    List<Nav> getAll();
    @Select("select * from menu where menu_id = #{menu_id}")
    Nav getOneNav(int menu_id);

    @Delete("DELETE FROM `menu` WHERE (`menu_id`=#{id})")
    Integer DeleteOneNav(int id);

    //此处表ID要增加Unique索引 参考语句 ALTER TABLE `TableName` ADD unique(`id`);
    //存在则更新,影响记录条数2   不存在则插入,影响记录条数1
    @Insert("insert INTO menu" +
            "(menu_id, is_show, menu_icon, menu_name,menu_url,parent_id,role_id) " +
            "VALUES (#{menu_id},#{is_show},#{menu_icon},#{menu_name},#{menu_url},#{parent_id},#{role_id}) " +
            "ON DUPLICATE KEY " +
            "UPDATE " +
            "menu_id = #{menu_id},is_show = #{is_show},menu_icon=#{menu_icon}, menu_name=#{menu_name},menu_url=#{menu_url},parent_id=#{parent_id},role_id=#{role_id}")
    Integer insertNav(Nav nav);
}
