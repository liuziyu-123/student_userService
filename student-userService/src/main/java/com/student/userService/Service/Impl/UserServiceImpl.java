package com.student.userService.Service.Impl;

import com.student.userService.Domain.Entry.UserEntry;
import com.student.userService.Mapper.UserMapper;
import com.student.userService.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserEntry userLogin(String mobile, String password) {

        UserEntry user = userMapper.userLogin(mobile, password);

        return user;
    }


}
