package com.student.userService.Config;


import com.student.userService.Interceptor.GlobelInterceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new GlobelInterceptor()).addPathPatterns("/**").excludePathPatterns("/user/**");
        //.excludePathPatterns("/user/**");
    }
}
