package com.student.userService.Service;


import com.student.userService.Dao.User;


public interface IUserService {

    User userLogin(String mobile, String password);
}
