package com.tsvico.dao;

import com.tsvico.pojo.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/9/18 10:35
 * 功能
 */
@Repository
public interface UserDao {

    //有没有这一个一个用户
    @Select("select * from users where uname = #{uname}")
    User findUserByusername(String uname);

    @Select("select * from users where uid = #{id}")
    @Results({
            @Result(column="dept_id",property="depart",
                    one=@One(
                            select="com.tsvico.dao.DepartDao.selectDeptById",
                            fetchType= FetchType.EAGER
                    )),
            @Result(column="position_id",property="position",
                    one=@One(
                            select="com.tsvico.dao.PositionDao.selectPositionByid",
                            fetchType= FetchType.EAGER
                    )),
    }) //需要指定类型,如果不指定会丢失两个元素
    User findUserByUid(int id);

    @Select("select * from users where uname=#{username} and upassword=#{password}")
    User findUser(@Param("username") String username,@Param("password") String password); //@Param指定参数名,防止出错

    @Delete("DELETE FROM `users` WHERE (`uid`=#{id})")
    int delect(int id);

    //此处表ID要增加Unique索引 参考语句 ALTER TABLE `TableName` ADD unique(`id`);
    //存在则更新,影响记录条数2   不存在则插入,影响记录条数1
    @Insert("insert INTO users" +
            "(uid, uname, upassword,unickname,status,uage,avater,email,dept_id,position_id,role_id) " +
            "VALUES (#{uid},#{uname},#{upassword},#{unickname},#{status},#{uage},#{avater},#{email},#{depart.dept_id},#{position.position_id},#{role_id}) " +
            "ON DUPLICATE KEY " +
            "UPDATE " +
            "uname=#{uname},upassword=#{upassword},unickname=#{unickname},status=#{status},uage=#{uage},avater=#{avater},email=#{email},dept_id=#{depart.dept_id},position_id=#{position.position_id},role_id=#{role_id}")
    Integer insertUser(User user);

    @Select("select * from users where role_id > #{role_id} and status = 1")
    @Results({
            @Result(id=true,column="uid",property="uid"), //列和属性相同可以省略???
            @Result(column="uname",property="uname"),
            @Result(column="unickname",property="unickname"),
            @Result(column="upassword",property="upassword"),
            @Result(column="uage",property="uage"),
            @Result(column = "avater",property = "avater"),
            @Result(column = "email",property = "email"),
            @Result(column="dept_id",property="depart",
                    one=@One(
                            select="com.tsvico.dao.DepartDao.selectDeptById",
                            fetchType= FetchType.EAGER
                    )),
            @Result(column="position_id",property="position",
                    one=@One(
                            select="com.tsvico.dao.PositionDao.selectPositionByid",
                            fetchType= FetchType.EAGER
                    )),
    })
    List<User> getAllUser(User user);

    /**
     * 离职员工
     */
    @Select("select * from users where role_id > #{role_id} and status = 0")
    @Results({
            @Result(id=true,column="uid",property="uid"), //列和属性相同可以省略???
            @Result(column="uname",property="uname"),
            @Result(column="upassword",property="upassword"),
            @Result(column="uage",property="uage"),
            @Result(column = "avater",property = "avater"),
            @Result(column = "email",property = "email"),
            @Result(column="dept_id",property="depart",
                    one=@One(
                            select="com.tsvico.dao.DepartDao.selectDeptById",
                            fetchType= FetchType.EAGER
                    )),
            @Result(column="position_id",property="position",
                    one=@One(
                            select="com.tsvico.dao.PositionDao.selectPositionByid",
                            fetchType= FetchType.EAGER
                    )),
    })
    List<User> getlevelUser(User user);
}
