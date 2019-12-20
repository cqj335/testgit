package com.hstc.config;

import com.hstc.interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Administrator on 2019/9/23.
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {


    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //往拦截器的注册表中添加需要的拦截器
        //并设置拦截地址为所有的，排除student地址不拦截
        registry.addInterceptor(authInterceptor).addPathPatterns("/*").excludePathPatterns("/login").excludePathPatterns("/mlogin").excludePathPatterns("/register").excludePathPatterns("/css/*").excludePathPatterns("/js/*").excludePathPatterns("/font/*").excludePathPatterns("/img/*").excludePathPatterns("/images/*").excludePathPatterns("/user");
    }
}
