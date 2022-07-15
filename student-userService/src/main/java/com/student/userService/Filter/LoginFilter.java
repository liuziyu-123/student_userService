package com.student.userService.Filter;

import com.alibaba.fastjson.JSONObject;
import com.student.userService.Utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * 登录过滤器
 */
@WebFilter("/user/login")
@Order(1)
public class LoginFilter implements Filter {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        redisUtil=ctx.getBean(RedisUtil.class);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        //检验用户登录信息
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;

        //Filter 过滤器跨域处理
        String origin =request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Methods","POST, GET, PUT, OPTIONS, DELETE, PATCH");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers","token,Origin, X-Requested-With, Content-Type,Accept,Authorization");
        response.setHeader("Access-Control-Allow-Credentials0","true");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        //获取Header中的token
        String token =request.getHeader("token");
        token=token==null ? "" : token;
        //查询Redis中是否存在
        long expireTime = redisUtil.getExpire(token);
        if(expireTime>0){
            //重置token 的时间
            redisUtil.expire(token,60000*30);
            //已登录  放行
            chain.doFilter(servletRequest,servletResponse);
        }else{
            //未登录，响应数据
            String data = JSONObject.toJSONString(-200);
            PrintWriter out=response.getWriter();
            out.write(data);
        }

    }

    @Override
    public void destroy() {

    }
}
