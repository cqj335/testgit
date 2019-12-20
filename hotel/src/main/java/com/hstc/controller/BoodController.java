package com.hstc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hstc.entity.Book;
import com.hstc.service.BoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;

/**
 * Created by Administrator on 2019/11/15.
 */
@Controller
public class BoodController {
    @Autowired
    private BoodService boodService;

    @GetMapping("bood")
    public ModelAndView findAll(Integer page, Integer size) {
        if (page == null) page = 1;
        if (size == null) size = 2;
        ModelAndView mav = new ModelAndView();
        mav.setViewName("bood");
        IPage<Book> iPage0 = boodService.findByPage(page, size);

        mav.addObject("iPage", iPage0);
        mav.addObject("pages",iPage0.getPages());
        return mav;


    }

    @RequestMapping("sear")
    public ModelAndView findAllu(String content,Integer page, Integer size) {
        if (page == null) page = 1;
        if (size == null) size = 10;
        ModelAndView mav = new ModelAndView();
        mav.setViewName("bood");
        IPage<Book> iPage0 = boodService.sear(content,page, size);

        mav.addObject("iPage", iPage0);
        mav.addObject("pages",iPage0.getPages());
        return mav;


    }
    /*@GetMapping ("bood/{bid}")
    public ModelAndView sendBook(@PathVariable Integer bid) {
        //获得想要的数据
        Book bood = boodService.findByBid(bid);
        String i="1";
        bood.setBremark(i);
        boolean isok = boodService.update(bood);
        System.out.println("更新结果为" + isok);
        ModelAndView mav = new ModelAndView();
        //存入mav，下发到网页中
        *//*mav.addObject("bood", bood);*//*
        mav.setViewName("redirect:../bood");
        return mav;
    }*/

    @GetMapping("bood1/{bid}")
    public ModelAndView editBook(@PathVariable Integer bid) {
        //获得单个学生信息
        Book bood = boodService.findByBid(bid);

        ModelAndView mav = new ModelAndView();
        //存入mav，下发到网页中
        mav.addObject("bood", bood);
        mav.setViewName("editbood");

        return mav;
    }

    @PutMapping("bood")
    public ModelAndView updBook(Book bood) {

        boolean isok = boodService.update(bood);
        System.out.println("更新结果为" + isok);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:bood");
        return mav;
    }

    @DeleteMapping("bood/{bid}")
    public ModelAndView delete(@PathVariable Integer bid) {
        boolean b = boodService.delete(bid);
        System.out.println(b);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:../bood");
        return mav;
    }

    @GetMapping("saveRoom")
    @ResponseBody
    public String findBook(Integer bid,Integer roomnum) throws JsonProcessingException, ParseException {
        Book bood=boodService.findByBid(bid);
        bood.setRoomnum(roomnum);
        String i="1";
        bood.setBremark(i);
        boolean isok = boodService.update(bood);
        System.out.println("更新结果为" + isok);

        ObjectMapper mapper=new ObjectMapper();

        return mapper.writeValueAsString(bood);

    }

}
