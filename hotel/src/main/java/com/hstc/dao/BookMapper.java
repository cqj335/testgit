package com.hstc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hstc.entity.Book;
import com.hstc.entity.Roomtype;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2019/9/29.
 */
public interface BookMapper extends BaseMapper<Book> {

    @Select("")
    public List<Roomtype> findRestRoom();
}
