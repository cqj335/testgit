package com.hstc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hstc.entity.Book;
import com.hstc.entity.Income;
import com.hstc.entity.User;
import com.hstc.service.BookService;
import com.hstc.service.IncomeService;
import io.micrometer.core.instrument.util.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.xml.crypto.Data;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created byinistrator on 2019/9/29.
 */
@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private IncomeService incomeService;

    @GetMapping("book")
    public ModelAndView findAll(Integer page, Integer size) {
        if (page == null) page = 1;
        if (size == null) size = 2;
        ModelAndView mav = new ModelAndView();
        mav.setViewName("book");
        IPage<Book> iPage0 = bookService.findByPage(page, size);
        mav.addObject("iPage", iPage0);
        mav.addObject("pages",iPage0.getPages());
        return mav;

    }

        @GetMapping ("book/{bid}")
    public ModelAndView sendBook(@PathVariable Integer bid) {
        //获得想要的数据
        Book book = bookService.findByBid(bid);
        Income income = new Income();
        income.setBianhao(book.getBianhao());
        income.setRealname(book.getRealname());
        income.setPhone(book.getPhone());
        income.setIdcard(book.getIdcard());
        income.setLeavedate(book.getLeavedate());
        income.setTprice(book.getTprice());

        int i = incomeService.saveIncome(income);
        if (i==1){
            int j = bookService.deleteById(bid);
        }

        ModelAndView mav = new ModelAndView();
        //存入mav，下发到网页中
        mav.addObject("book", book);
        mav.setViewName("redirect:../book");
        return mav;
    }

    @GetMapping("addbook")
    public ModelAndView getBook() {

        ModelAndView mav = new ModelAndView();
        mav.setViewName("addbook");
        return mav;

    }

    @PostMapping("book")
    public ModelAndView saveBook(Book book) {

        //执行插入操作
        boolean isok = bookService.save(book);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:book");
        return mav;

    }
    @GetMapping("book1/{bid}")
    public ModelAndView editBook(@PathVariable Integer bid) {
        //获得单个学生信息
        Book book = bookService.findByBid(bid);

        ModelAndView mav = new ModelAndView();
        //存入mav，下发到网页中
        mav.addObject("book", book);
        mav.setViewName("editbook");

        return mav;
    }

    @PutMapping("book")
    public ModelAndView updBook(Book book) {

        boolean isok = bookService.update(book);
        System.out.println("更新结果为" + isok);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:book");
        return mav;
    }

    @DeleteMapping("book/{bid}")
    public ModelAndView delete(@PathVariable Integer bid) {
        boolean b = bookService.delete(bid);
        System.out.println(b);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:../book");
        return mav;
    }

    @GetMapping("order")
    public ModelAndView findAlle(HttpSession session) {
        User user =
                (User) session.getAttribute("user");
        String uname=user.getUname();
        ModelAndView mav = new ModelAndView();
        mav.setViewName("order");
        List<Book>list=bookService.findbyUname(uname);
        mav.addObject("list", list);
        return mav;

    }

    @GetMapping("order/{bid}")
    public ModelAndView editOrder(@PathVariable Integer bid) {
        //获得单个学生信息
        Book book = bookService.findByBid(bid);

        ModelAndView mav = new ModelAndView();
        //存入mav，下发到网页中
        mav.addObject("book", book);
        mav.setViewName("editorder");

        return mav;
    }

    @GetMapping("eorder")
    public ModelAndView deleteOrder(Integer bid){
        Book book=bookService.findByBid(bid);
        Date date1=book.getArrivedate();
        Date date=new Date();
        ModelAndView mav=new ModelAndView();
        if (date.getTime()>=date1.getTime()){
            mav.setViewName("redirect:order");
            return mav;
        }else {
            boolean b = bookService.delete(bid);
            mav.setViewName("redirect:order");
            return mav;
        }
    }

    @GetMapping("orderRoom")
    @ResponseBody
    public String findBook(Integer bid) throws JsonProcessingException, ParseException {
        Book book=bookService.findByBid(bid);
        System.out.println(book);
        Date date1=book.getArrivedate();
        Date date=new Date();
        ModelAndView mav=new ModelAndView();
        if (date.getTime()>=date1.getTime()){
            ObjectMapper mapper=new ObjectMapper();
            Boolean isok=false;
            System.out.println(isok);
            return mapper.writeValueAsString(isok);
        }else {
            ObjectMapper mapper=new ObjectMapper();
            Boolean isok=true;
            System.out.println(isok);
            return mapper.writeValueAsString(isok);
        }


    }
//    @PostMapping("/pay")
//    @ResponseBody
//    public void saveBookform(@Valid Book book, BindingResult bindingResult, HttpSession session) throws JsonProcessingException, ParseException {
//
//        boolean i=bookService.save(book);
//        String msg=null;
//        if (i==true){
//            msg="下单成功";
//
//        }else {
//            msg="下单失败，请重试";
//        }
//        return msg;
//    }

    /*@GetMapping("myOrder")
    @ResponseBody
    public String myOrder(String uname) throws JsonProcessingException {
        List<Book> list=bookService.findbyUname(uname);
        ObjectMapper mapper=new ObjectMapper();
        return mapper.writeValueAsString(list);
    }*/

    /*@GetMapping("tuikuan")
    @ResponseBody
    public String myOrderi(String bianhao) throws JsonProcessingException {
    List<Book>list=bookService.findbyUname(bianhao);
        System.out.println(bianhao);
        ObjectMapper mapper=new ObjectMapper();
        return mapper.writeValueAsString(list);
    }*/

    @RequestMapping("search")
    @ResponseBody
    public ModelAndView search(String content,Integer page, Integer size){
        if (page == null) page = 1;
        if (size == null) size = 10;
        ModelAndView mav = new ModelAndView();
        mav.setViewName("book");
        IPage<Book> iPage0 = bookService.search(content,page, size);
        mav.addObject("iPage", iPage0);
        mav.addObject("pages",iPage0.getPages());
        return mav;

    }
}
