package com.hstc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hstc.dao.BoodMapper;
import com.hstc.entity.Book;
import com.hstc.service.BoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2019/11/15.
 */
@Service
public class BoodServiceImpl implements BoodService {
    @Autowired
    private BoodMapper boodMapper;

    @Override
    public IPage<Book> findByPage(Integer page, Integer size) {
        return boodMapper.selectPage(new Page<Book>(page, size), new QueryWrapper<Book>()
                .eq("bremark",0));
    }

    @Override
    public Book findByBid(Integer bid) {
        return boodMapper.selectById(bid);
    }

    @Override
    public boolean update(Book bood) {
        int k=boodMapper.updateById(bood);
        return k==1?true:false;
    }

    @Override
    public boolean delete(Integer bid) {
        int i = boodMapper.deleteById(bid);
        return i==1?true:false;
    }

    @Override
    public IPage<Book> sear(String content, Integer page, Integer size) {
        return boodMapper.selectPage(new Page<Book>(page, size), new QueryWrapper<Book>()
                .eq("bremark",0).like("realname",content));
    }
}
