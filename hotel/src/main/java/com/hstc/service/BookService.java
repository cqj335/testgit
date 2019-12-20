package com.hstc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hstc.entity.Book;

import java.util.List;

/**
 * Created by Administrator on 2019/9/29.
 */
public interface BookService {
    public IPage<Book> findByPage(Integer page, Integer size);

    public List<Book> findAll();

    public Book findByBid(Integer bid);

    public int deleteById(Integer bid);

    public boolean save(Book book);

    public boolean update(Book book);

    public boolean delete(Integer bid);

    public List<Book> findbyUname(String uname);

    public List<Book> selectbyid(String tname);

    public void save1(Book book);

    public List<Book> findbyRoomnum(Integer roomnum);

    public IPage<Book> search(String content,Integer page, Integer size);
}

