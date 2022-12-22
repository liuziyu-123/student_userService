package com.student.userService.Interceptor;

import com.student.userService.Domain.Entry.UserEntry;
import com.student.userService.Utils.JwtHelper;
import com.student.userService.Utils.LocalThread;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 */
@Slf4j
@Component
public class GlobelInterceptor implements HandlerInterceptor {


    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        try {
            //获取userInfo
            String userJson = request.getHeader("token");

            if (StringUtils.isNotEmpty(userJson)) {
                String userName = JwtHelper.getUserName(userJson);
                String userId = JwtHelper.getUserId(userJson);
                String userNo = JwtHelper.getUserNo(userJson);
                UserEntry userInfo = new UserEntry();
                userInfo.setId(userId);
                userInfo.setUserName(userName);
                userInfo.setUserNo(userNo);
                LocalThread.set(userInfo);
                System.out.println(LocalThread.get());
            }
            return Boolean.TRUE;
        } catch (Exception e) {
            log.error("拦截器出错", e);
            return Boolean.FALSE;
        }
    }

    /**
     * 请求处理之后进行调用（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView mv)
            throws Exception {

    }

    /**
     * 在整个请求结束之后被调用（主要是用于进行资源清理工作）
     * 一定要在请求结束后调用remove清除当前线程的副本变量值，否则会造成内存泄漏
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception ex)
            throws Exception {
        LocalThread.remove();
    }

}
