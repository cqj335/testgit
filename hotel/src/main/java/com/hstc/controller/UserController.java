package com.hstc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hstc.entity.User;
import com.hstc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("findUname")
    @ResponseBody
    public String findUname(String username) throws JsonProcessingException {
         User user=userService.findUname(username);
       // System.out.println("用户名2"+user.getUname());
       // System.out.println("用户名"+username);
        Map<String,Object> map=new HashMap<String, Object>();
        //不等于null的时候才获取uname的值，这样不会空指针异常
        if (user!=null){
            String uname=user.getUname();

            if (uname.equals(username)) {
                // System.out.println("Forrest");
                map.put("userExit",true);
                map.put("msg","此用户名太受欢迎，请更换一个");
            }else{
                map.put("userExit",false);
                map.put("msg","✔");
            }
            ObjectMapper mapper=new ObjectMapper();
            return  mapper.writeValueAsString(map);
        }else{
            map.put("userExit",false);
            map.put("msg","✔");
            ObjectMapper mapper=new ObjectMapper();
            return  mapper.writeValueAsString(map);
        }
    }

    @GetMapping("register")
    public ModelAndView intoregister(){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("register");
        return mav;
    }
    @PostMapping("adduser")
public ModelAndView register(User user){
        ModelAndView mav=new ModelAndView();
        userService.register(user);
        mav.setViewName("login");
        return mav;
    }


    @GetMapping("login")
    public ModelAndView intologin(){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("login");
        return mav;
    }
    @PostMapping("user")
    public ModelAndView login(String uname, String upassword, Integer ustate, HttpSession session) {
         ModelAndView mav= new ModelAndView();
        System.out.println( "***************"+upassword+uname+ustate);
        User user=userService.findByUnameAndPassword(uname,upassword,ustate);
        if (user==null&&ustate==1){
            mav.setViewName("login");
            mav.addObject("msg","登录失败，用户名或者密码或者身份错误！");
        }else if(user==null&&ustate==0){
            mav.setViewName("mlogin");
            mav.addObject("msg","登录失败，用户名或者密码或者身份错误！");
        }
        else if(ustate==1){
            session.setAttribute("user",user);
            session.setAttribute("phone",user.getPhone());
            session.setAttribute("uname",user.getUname());
            session.setAttribute("upassword",user.getUpassword());
            session.setAttribute("ustate",user.getUstate());
            mav.setViewName("redirect:userRoomType");
        }else{
            session.setAttribute("user",user);
            session.setAttribute("uname",user.getUname());
            session.setAttribute("upassword",user.getUpassword());
            mav.setViewName("redirect:system");
            session.setAttribute("ustate",user.getUstate());
        }
        return mav;
    }
    @RequestMapping("loginOut")
    public String loginOut(HttpSession session){
            session.invalidate();
            return "redirect:login";
    }
    @PostMapping("edtUser")
    @ResponseBody
    public String edtUser(User user,HttpSession session) throws JsonProcessingException {
         userService.edtUser(user);
         session.setAttribute("user",user);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("isok","ok");
        map.put("msg","修改成功");
        ObjectMapper mapper=new ObjectMapper();
         return mapper.writeValueAsString(map);
    }
    @GetMapping("mlogin")
    public ModelAndView intomlogin(){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("mlogin");
        return mav;
    }
    @GetMapping("system")
    public ModelAndView system(){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("system");
        return mav;
    }
    @GetMapping("main")
    public ModelAndView main(){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("main");
        return mav;
    }
}
