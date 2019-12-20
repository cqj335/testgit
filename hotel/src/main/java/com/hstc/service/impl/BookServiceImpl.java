package com.hstc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hstc.dao.BookMapper;
import com.hstc.entity.Book;
import com.hstc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2019/9/29.
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Override
    public IPage<Book> findByPage(Integer page, Integer size) {
        return bookMapper.selectPage(new Page<Book>(page, size), new QueryWrapper<Book>()
        .eq("bremark",1));
    }

    @Override
    public List<Book> findAll() {
        return bookMapper.selectList(null);
    }

    @Override
    public Book findByBid(Integer bid) {
        return bookMapper.selectById(bid);
    }

    @Override
    public int deleteById(Integer bid) {
        QueryWrapper qw = new QueryWrapper<Book>();
        qw.eq("bid",bid);
        int i = bookMapper.delete(qw);
        return i;
    }

    @Override
    public boolean save(Book book) {
        int i=bookMapper.insert(book);
        return i==1?true:false;
    }

    @Override
    public boolean update(Book book) {
        int i=bookMapper.updateById(book);
        return i==1?true:false;
    }

    @Override
    public boolean delete(Integer bid) {
        int i = bookMapper.deleteById(bid);
        return i==1?true:false;
    }

    @Override
    public List<Book> findbyUname(String uname) {
        QueryWrapper<Book> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("uname",uname);
        return bookMapper.selectList(queryWrapper);
    }

    @Override
    public List<Book> selectbyid(String tname) {
        QueryWrapper<Book> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("tname",tname);
        return bookMapper.selectList(queryWrapper);
    }

    @Override
    public void save1(Book book) {
        bookMapper.insert(book);
    }

    @Override
    public List<Book> findbyRoomnum(Integer roomnum) {
        QueryWrapper<Book> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("roomnum",roomnum);
        return bookMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<Book> search(String content,Integer page, Integer size) {
        return bookMapper.selectPage(new Page<Book>(page, size), new QueryWrapper<Book>()
                .eq("bremark",1).like("realname",content));
    }
}
