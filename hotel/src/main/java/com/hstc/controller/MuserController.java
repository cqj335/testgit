package com.hstc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hstc.entity.User;
import com.hstc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2019/10/10.
 */
@Controller
public class MuserController {
    @Autowired
    private UserService userService;

    @GetMapping("muser")
    public ModelAndView findAll(Integer page, Integer size) {
        if (page == null) page = 1;
        if (size == null) size = 2;
        ModelAndView mav = new ModelAndView();
        mav.setViewName("muser");
        IPage<User> iPage0 = userService.findByPage(page, size);
        mav.addObject("iPage", iPage0);
        mav.addObject("pages",iPage0.getPages());
        return mav;

    }

    @RequestMapping("searcename")
    public ModelAndView findAllu(String content,Integer page, Integer size) {
        if (page == null) page = 1;
        if (size == null) size = 10;
        ModelAndView mav = new ModelAndView();
        mav.setViewName("muser");
        IPage<User> iPage0 = userService.findBySearcename(content,page, size);
        mav.addObject("iPage", iPage0);
        mav.addObject("pages",iPage0.getPages());
        return mav;

    }

    @GetMapping("adduser")
    public ModelAndView getIncome() {

        ModelAndView mav = new ModelAndView();
        mav.setViewName("adduser");
        return mav;

    }

    @PostMapping("muser")
    public ModelAndView saveIncome(User user) {

        //执行插入操作
        boolean isok = userService.save(user);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:muser");
        return mav;

    }

    @GetMapping("muser/{uid}")
    public ModelAndView editIncome(@PathVariable Integer uid) {
        //获得单个学生信息
        User muser = userService.findByuid(uid);

        ModelAndView mav = new ModelAndView();
        //存入mav，下发到网页中
        mav.addObject("muser", muser);
        mav.setViewName("edituser");

        return mav;
    }

    @PutMapping("muser")
    public ModelAndView updIncome(User muser) {

        boolean isok = userService.update(muser);
        System.out.println("更新结果为" + isok);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:muser");
        return mav;
    }

    @DeleteMapping("muser/{uid}")
    public ModelAndView delete(@PathVariable Integer uid) {
        boolean b = userService.delete(uid);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:../muser");
        return mav;
    }

}
