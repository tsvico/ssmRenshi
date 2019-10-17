package com.tsvico.dao;

import com.tsvico.pojo.PunchClock;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
    @Update("update sys_userpunchclock set " +
            "punch_outTime=#{punch_outTime} " +
            "where " +
            "attendanceTime=#{attendanceTime} and userid=#{userid}")
    int up_out(PunchClock punchClock);

    /**
     * 签退
     * @param punchClock
     * @return
     */
    @Insert("insert into sys_userpunchclock(id,userid,developername,punch_inTime,attendanceTime,userip,loginaddress) " +
            "values(null,#{userid},#{developername},#{punch_inTime},#{attendanceTime},#{userip},#{loginaddress}")
    int add_in(PunchClock punchClock);


    /**
     * //迟到原因备注
     * @param punchClock
     * @return
     */
    @Update("update sys_userpunchclock set " +
            "remark=#{remark} where attendanceTime=#{attendanceTime} and userid=#{userid}\n")
    int late_result(PunchClock punchClock);


    /**
     * //查询当前用户是否已经打卡
     * @param punchClock
     * @return
     */
    @Select("select punch_inTime from sys_userpunchclock where attendanceTime=#{attendanceTime} and userid=#{userid}")
    PunchClock if_punchin(PunchClock punchClock);


    /**
     * //查询当前用户是否已经签退
     * @param punchClock
     * @return
     */
    @Select("select punch_outTime from sys_userpunchclock where attendanceTime=#{attendanceTime} and userid=#{userid}")
    PunchClock if_punchout(PunchClock punchClock);

    /**
     * 显示所有记录 //查询最新数据
     * @param offsetLeft 偏移量
     * @param offsetright 偏移量
     * @return
     */
    @Select("SELECT * FROM `punchclock` ORDER BY id DESC LIMIT #{offsetLeft}, #{offsetright}")
    List<PunchClock> find_punchout_All(@Param("offsetLeft") int offsetLeft,@Param("offsetright") int offsetright);
}
