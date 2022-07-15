package com.student.userService.Filter;


import com.alibaba.fastjson.JSONObject;
import com.student.userService.Utils.ApiResult;
import com.student.userService.Utils.MyException;
import com.student.userService.Utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter("/**")
public class GlobleFilter implements Filter {
    private String map;

    @Autowired
    private  RedisUtil redisUtil;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器执行了。。。。。。");
        ServletContext servletContext = filterConfig.getServletContext();
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        map = ctx.getEnvironment().getProperty("white.list.login");
        redisUtil=ctx.getBean(RedisUtil.class);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("过滤器执行了");
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        //Filter 过滤器跨域处理
        String origin =request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Methods","POST, GET, PUT, OPTIONS, DELETE, PATCH");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers","token,Origin, X-Requested-With, Content-Type,Accept,Authorization");
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        //获取Header中的token
        String uri=request.getRequestURI();
        if(!uri.equals(map)){
            String token =request.getHeader("token");
            if(token==null){
                throw new MyException("没有Token");
            }
            //查询Redis中是否存在
            long expireTime = redisUtil.getExpire(token);
            if(expireTime>0){
                //重置token 的时间
                redisUtil.expire(token,60000*30);
                //已登录  放行
                filterChain.doFilter(servletRequest,servletResponse);
            }else{
                //未登录，响应数据
                ApiResult apiResult=new ApiResult(-200,"token无效");
                String data = JSONObject.toJSONString(apiResult);
                PrintWriter out=response.getWriter();
                out.write(data);
            }

        }else{
            filterChain.doFilter(servletRequest,servletResponse);
        }

    }

    @Override
    public void destroy() {
        System.out.println("过滤器结束了");
    }
}
