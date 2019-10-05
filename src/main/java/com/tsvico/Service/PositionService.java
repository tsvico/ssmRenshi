package com.tsvico.Service;

import com.tsvico.pojo.Position;
import com.tsvico.pojo.Role;

import java.util.List;

/**
 * @author tsvico
 * @email tsxygwj@gmail.com
 * @time 2019/9/23 16:41
 * 功能
 */
public interface PositionService {
    List<Position> getAll();
    Integer deletePosition(int id);
    int updatePosition(Position position);
    List<Role> getAllRole(); //查所有职位大类
}
