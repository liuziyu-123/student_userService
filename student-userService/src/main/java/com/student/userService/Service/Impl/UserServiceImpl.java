package com.student.userService.Service.Impl;

import com.student.userService.Dao.User;
import com.student.userService.Mapper.UserMapper;
import com.student.userService.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User userLogin(String mobile, String password) {

        User user=userMapper.userLogin(mobile,password);

        return user;
    }


}
