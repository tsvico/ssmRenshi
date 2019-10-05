package com.tsvico.Service.Impl;

import com.tsvico.Service.PositionService;
import com.tsvico.dao.PositionDao;
import com.tsvico.pojo.Position;
import com.tsvico.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/9/23 16:41
 * 功能
 */
@Service
public class PositionServiceImpl implements PositionService {
    @Autowired
    private PositionDao positionDao;

    @Override
    public List<Position> getAll() {
        return positionDao.getAllPosition();
    }

    @Override
    public Integer deletePosition(int id) {
        return positionDao.deleteByOne(id);
    }

    @Override
    public int updatePosition(Position position) {
        return positionDao.updateByOne(position);
    }

    @Override
    public List<Role> getAllRole() {
        return positionDao.getAllRole();
    }
}
