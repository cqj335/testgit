package com.hstc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hstc.entity.Book;

/**
 * Created by Administrator on 2019/11/15.
 */
public interface BoodService {
    public IPage<Book> findByPage(Integer page, Integer size);

    public Book findByBid(Integer bid);

    public boolean update(Book bood);

    public boolean delete(Integer bid);

    public IPage<Book> sear(String content, Integer page, Integer size);
}
