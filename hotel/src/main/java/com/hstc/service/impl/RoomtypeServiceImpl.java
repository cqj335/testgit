package com.hstc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hstc.dao.RoomtypeMapper;
import com.hstc.entity.Roomtype;
import com.hstc.service.RoomtypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2019/9/25.
 */
@Service
public class RoomtypeServiceImpl implements RoomtypeService {

    @Autowired
    private RoomtypeMapper roomtypeMapper;

    @Override
    public List<Roomtype> findAll() {
        return roomtypeMapper.selectList(null);
    }

    @Override
    public boolean save(Roomtype roomtype) {
        int i = roomtypeMapper.insert(roomtype);
        return i==1?true:false;
    }

    @Override
    public Roomtype findByTid(Integer tid) {
        return roomtypeMapper.selectById(tid);
    }

    @Override
    public boolean update(Roomtype roomtype) {
        int i = roomtypeMapper.updateById(roomtype);
        return i==1?true:false;
    }

    @Override
    public boolean delete(Integer tid) {
        int i = roomtypeMapper.deleteById(tid);
        return i==1?true:false;
    }

    @Override
    public IPage<Roomtype> findByPage(Integer currPage, Integer pageSize) {
        return roomtypeMapper.selectPage(new Page<>(currPage, pageSize), null);
    }


}
