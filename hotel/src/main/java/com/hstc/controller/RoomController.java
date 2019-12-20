package com.hstc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hstc.entity.Book;
import com.hstc.service.BoodService;
import com.hstc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.util.List;

/**
 * Created by Administrator on 2019/11/15.
 */
@Controller
public class RoomController {
    @Autowired
    private BookService bookService;

    @GetMapping("room")
    public ModelAndView findAll(Integer page, Integer size) {
        if (page == null) page = 1;
        if (size == null) size = 2;
        ModelAndView mav = new ModelAndView();
        mav.setViewName("room");
        IPage<Book> iPage = bookService.findByPage(page, size);
        Long pages = 0L;
        if (iPage.getSize() == 0L) {
            pages = 0L;
        } else {
            pages = iPage.getTotal() / iPage.getSize();
            if (iPage.getTotal() % iPage.getSize() != 0L) {
                ++pages;
            }

        }
        mav.addObject("iPage", iPage);
        mav.addObject("pages",pages);
        return mav;

    }

    @GetMapping("myModal")
    @ResponseBody
    public String myModal(Integer roomnum) throws JsonProcessingException {
        List<Book> list=bookService.findbyRoomnum(roomnum);
        ObjectMapper mapper=new ObjectMapper();
        return mapper.writeValueAsString(list);
    }
}
