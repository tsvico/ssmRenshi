package com.tsvico.Service.Impl;

import com.tsvico.Service.PunchClockService;
import com.tsvico.dao.PunchClockDao;
import com.tsvico.pojo.PunchClock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/10/17 15:31
 * 功能
 */
@Service
public class PunchClockServiceImpl implements PunchClockService {
    @Autowired
    private PunchClockDao punchClockDao;

    @Override
    public int up_out(PunchClock punchClock) {
        return punchClockDao.up_out(punchClock);
    }

    @Override
    public int add_in(PunchClock punchClock) {
        return punchClockDao.add_in(punchClock);
    }

    @Override
    public PunchClock if_punchin(PunchClock punchClock) {
        return punchClockDao.if_punchin(punchClock);
    }

    @Override
    public PunchClock if_punchout(PunchClock punchClock) {
        return punchClockDao.if_punchout(punchClock);
    }

    @Override
    public List<PunchClock> findPunchClockAll(int offsetLeft, int offsetright) {
        return punchClockDao.find_punchout_All(offsetLeft,offsetright);
    }

    @Override
    public int late_result(PunchClock punchClock) {
        return punchClockDao.late_result(punchClock);
    }
}
