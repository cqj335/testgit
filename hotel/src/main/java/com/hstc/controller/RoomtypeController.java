package com.hstc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hstc.entity.Book;
import com.hstc.entity.Roomtype;
import com.hstc.service.BookService;
import com.hstc.service.RoomtypeService;
import com.hstc.utills.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Date;
import java.util.List;


/**
 * Created by Administrator on 2019/9/25.
 */
@Controller
public class RoomtypeController {

    @Autowired
    private RoomtypeService roomtypeService;
    @Autowired
    private BookService bookService;

    @GetMapping("userRoomType")
    public ModelAndView findAllu(Integer page, Integer size) {
        if (page == null) page = 1;
        if (size == null) size = 2;
        ModelAndView mav = new ModelAndView();
        mav.setViewName("userRoomType");
        IPage<Roomtype> iPage = roomtypeService.findByPage(page, size);
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

    @GetMapping("roomtype")
    public ModelAndView findAll(Integer page, Integer size) {
        if (page == null) page = 1;
        if (size == null) size = 2;
        ModelAndView mav = new ModelAndView();
        mav.setViewName("roomtype");
        IPage<Roomtype> iPage = roomtypeService.findByPage(page, size);
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

    @GetMapping("addroomtype")
    public ModelAndView getRoomtyoe() {

        ModelAndView mav = new ModelAndView();
        mav.setViewName("addroomtype");
        return mav;

    }

    @PostMapping("roomtype")
    public ModelAndView saveRoomtype(Roomtype roomtype) {

        System.out.println(roomtype);
        //给图片名加入前缀
        String oldtphoto=roomtype.getTphoto();
        String tphoto="/img/"+oldtphoto;
        roomtype.setTphoto(tphoto);
        //执行插入操作
        boolean isok = roomtypeService.save(roomtype);
        System.out.println(isok);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:roomtype");
        return mav;

    }

    @GetMapping("roomtype/{tid}")
    public ModelAndView editStudent(@PathVariable Integer tid) {
        System.out.println("开始执行。。。");

        Roomtype roomtype = roomtypeService.findByTid(tid);
        /*//还原图片名
        String tphoto=roomtype.getTphoto();
        tphoto=tphoto.replace("/img/","");
        roomtype.setTphoto(tphoto);*/
        ModelAndView mav = new ModelAndView();
        //存入mav，下发到网页中
        mav.addObject("roomtype", roomtype);
        mav.setViewName("editroomtype");
        System.out.println("执行完成");
        return mav;
    }

    @PutMapping("roomtype")
    public ModelAndView updStudent(Roomtype roomtype) {

        boolean isok = roomtypeService.update(roomtype);
        System.out.println("更新结果为" + isok);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:roomtype");
        return mav;
    }

    @DeleteMapping("roomtype/{tid}")
    public ModelAndView delete(@PathVariable Integer tid) {
        boolean b = roomtypeService.delete(tid);
        System.out.println(b);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:../roomtype");
        return mav;
    }

    @GetMapping("findRoom")
    @ResponseBody
    public String findBook(Integer tid,String startDate,String endDate) throws JsonProcessingException, ParseException {
        Roomtype roomtype=roomtypeService.findByTid(tid);
        String tname=roomtype.getTname();
        List<Book> booklist=bookService.selectbyid(tname);
        Date date1= TimeUtils.getTime(startDate);
        Date date2=TimeUtils.getTime(endDate);
        Integer troomnum=roomtype.getTroomnum();
        int j=0;
        for (int i=0;i<booklist.size();i++) {
            Book book=booklist.get(i);
            Date arrivedate=book.getArrivedate();
            Date leavedate=book.getLeavedate();

            if (date1.getTime()>=leavedate.getTime()||date2.getTime()<=arrivedate.getTime()){
                j=0;
            }else{
                j++;
            }

        }

        if ((troomnum-j)>0){
            ObjectMapper mapper=new ObjectMapper();
            return mapper.writeValueAsString(roomtype);
        }else{
            ObjectMapper mapper=new ObjectMapper();
            Boolean isok=false;
            return mapper.writeValueAsString(isok);
        }
    }
}
