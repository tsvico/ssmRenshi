package com.tsvico.Service;

import com.tsvico.pojo.PunchClock;

import java.util.List;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/10/17 15:31
 * 功能
 */
public interface PunchClockService {
    int up_out(PunchClock punchClock);
    int add_in(PunchClock punchClock);
    int late_result(PunchClock punchClock);
    PunchClock if_punchin(PunchClock punchClock);
    PunchClock if_punchout(PunchClock punchClock);
    List<PunchClock> findPunchClockAll(int offsetLeft,int offsetright);
}
