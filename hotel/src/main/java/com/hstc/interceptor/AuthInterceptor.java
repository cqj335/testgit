package com.hstc.interceptor;

import com.hstc.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2019/9/23.
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(true);
        Object username=session.getAttribute("uname");
        Object password=session.getAttribute("upassword");
        Object state=session.getAttribute("ustate");
        System.out.println(username);
        if(null!=username&&null!=password) {//已登录
            return true;
        }else if(state.equals(0)){//未登录
            //直接重定向到登录页面
            response.sendRedirect(request.getContextPath()+"/mlogin");
            return false;
        }else{
            response.sendRedirect(request.getContextPath()+"/login");
            return false;
        }
    }
}
