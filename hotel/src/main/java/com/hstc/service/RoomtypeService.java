package com.hstc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hstc.entity.Roomtype;

import java.util.List;

/**
 * Created by Administrator on 2019/9/25.
 */

public interface RoomtypeService {

    public List<Roomtype> findAll();
    public boolean save(Roomtype roomtype);
    public Roomtype findByTid(Integer tid);
    public boolean update(Roomtype roomtype);
    public boolean delete(Integer tid);
    public IPage<Roomtype> findByPage(Integer currPage,Integer pageSize);
}
