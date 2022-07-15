package com.student.userService.Config;

import com.student.userService.Filter.GlobleFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean GlobelFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new GlobleFilter());
        registrationBean.addUrlPatterns("/*");
        
        registrationBean.setName("globle");
        registrationBean.setOrder(2);
        return registrationBean;
    }

//    @Bean
//    public FilterRegistrationBean registFilter(){
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        registrationBean.setFilter(new LoginFilter());
//        registrationBean.addUrlPatterns("/user/login");
//        registrationBean.setName("login");
//        registrationBean.setOrder(1);
//        return registrationBean;
//    }
}