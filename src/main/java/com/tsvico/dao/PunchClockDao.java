package com.tsvico.dao;

import com.tsvico.pojo.PunchClock;
import com.tsvico.pojo.PunchClockBb;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/10/17 15:29
 * 功能 考勤打卡
 */
@Repository
public interface PunchClockDao {

    /**
     * //打卡
     * @param punchClock
     * @return
     */
    @Update("update punchclock set " +
            "punch_outTime=#{punch_outTime} " +
            "where " +
            "attendanceTime=#{attendanceTime} and uid=#{uid}")
    int up_out(PunchClock punchClock);

    /**
     * 签退
     * @param punchClock
     * @return
     */
    @Insert("insert into punchclock(id,uid,developername,punch_inTime,attendanceTime,userip,loginaddress) " +
            "values(null,#{uid},#{developername},#{punch_inTime},#{attendanceTime},#{userip},#{loginaddress}")
    int add_in(PunchClock punchClock);


    /**
     * //迟到原因备注
     * @param punchClock
     * @return
     */
    @Update("update punchclock set " +
            "remark=#{remark} where attendanceTime=#{attendanceTime} and uid=#{uid}")
    int late_result(PunchClock punchClock);


    /**
     * //查询当前用户是否已经打卡
     * @param punchClock
     * @return
     */
    @Select("select punch_inTime from punchclock where attendanceTime=#{attendanceTime} and uid=#{uid}")
    PunchClock if_punchin(PunchClock punchClock);


    /**
     * //查询当前用户是否已经签退
     * @param punchClock
     * @return
     */
    @Select("select punch_outTime from punchclock where attendanceTime=#{attendanceTime} and uid=#{uid}")
    PunchClock if_punchout(PunchClock punchClock);

    /**
     * 显示所有记录 //查询最新数据
     * @param offsetLeft 偏移量
     * @param offsetright 偏移量
     * @return
     */
    @Select("SELECT * FROM `punchclock` ORDER BY id DESC LIMIT #{offsetLeft}, #{offsetright}")
    @Results({
            @Result(column="uid",property="user",
                    one=@One(
                            select="com.tsvico.dao.UserDao.findUserByid",
                            fetchType= FetchType.EAGER
                    ))
    })
    List<PunchClock> find_punchout_All(@Param("offsetLeft") int offsetLeft,@Param("offsetright") int offsetright);

    /**
     * 报表查询
     * @return
     */
    @Select("SELECT p.*,u.unickname,d.`name` as deparment FROM `punchclock` p,users u,department d where p.uid=u.uid and u.dept_id=d.dept_id ORDER BY u.dept_id DESC ")
    List<PunchClockBb> findBb();
}
