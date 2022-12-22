package com.student.userService.Service;


import com.student.userService.Domain.Entry.UserEntry;


public interface IUserService {


    /**
     * 用户登录
     *
     * @param mobile   手机号码
     * @param password 密码
     * @return
     */
    UserEntry userLogin(String mobile, String password);


}
