package com.student.userService.Filter;


import com.alibaba.fastjson.JSONObject;
import com.student.userService.Config.PropotiesConfiger;
import com.student.userService.Utils.ApiResult;
import com.student.userService.Utils.MyException;
import com.student.userService.Utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class GlobleFilter implements Filter {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private PropotiesConfiger propotiesConfiger;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("过滤器执行了........");
        ServletContext servletContext = filterConfig.getServletContext();
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
       // mapList = map(ctx);
        redisUtil = ctx.getBean(RedisUtil.class);
        propotiesConfiger=ctx.getBean(PropotiesConfiger.class);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("过滤器执行了");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //Filter 过滤器跨域处理
  //      String map=propotiesConfiger.getLogin();
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "token,Origin, X-Requested-With, Content-Type,Accept,Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        //获取Header中的token
        String uri = request.getRequestURI();
        for (String item : propotiesConfiger.getList().values()) {
            if (uri.equals(item) || uri.endsWith(".jpg")) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }
        String token = request.getHeader("token");
        if (token == null) {
            throw new MyException("没有Token");
        }
        //查询Redis中是否存在
        long expireTime = redisUtil.getExpire(token);
        if (expireTime > 0) {
            //重置token 的时间
            redisUtil.expire(token, 60000 * 30);
            //已登录  放行
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            //未登录，响应数据
            ApiResult apiResult = new ApiResult(-200, "token无效");
            String data = JSONObject.toJSONString(apiResult);
            PrintWriter out = response.getWriter();
            out.write(data);
        }
    }

    @Override
    public void destroy() {
        System.out.println("过滤器结束了");
    }


//    public List<String> map(ApplicationContext ctx) {
//        List<String> mapList = new ArrayList<>();
//        String login = ctx.getEnvironment().getProperty("white.list.login");
//        String upload = ctx.getEnvironment().getProperty("white.list.upload");
//        String fileUUID = ctx.getEnvironment().getProperty("white.list.fileUUID");
//        String outEx = ctx.getEnvironment().getProperty("white.list.outEx");
//        mapList.add(login);
//        mapList.add(upload);
//        mapList.add(fileUUID);
//        mapList.add(outEx);
//        return mapList;
//    }
}
