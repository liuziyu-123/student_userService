package com.student.userService.Service.Impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.student.userService.Dao.User;
import com.student.userService.Mapper.UserMapper;
import com.student.userService.Service.IUserService;
import com.student.userService.Utils.UUIDUtils;
import com.student.userService.Vo.UserVo;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;


@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User userLogin(String mobile, String password) {

        User user=userMapper.userLogin(mobile,password);

        return user;
    }

    /**
     * 获取用户列表
     * @param page  页数
     * @param pageSize  每页个数
     * @param userVo
     * @return
     */
    @Override
        public List<User> getTsInfo(int page, int pageSize, UserVo userVo) {

        if(page==0 || pageSize==0){
            return null;
        }
        if(pageSize>0){
            PageHelper.startPage(page,pageSize);
        }
        List<User> userList=userMapper.getUserList(userVo);
        return userList;
    }

    /**
     * 新增用户列表列表
     * @param user
     * @return
     */
    @Override
    public int insertTs(User user) {
        Long num=userMapper.selectCount(Wrappers.<User>lambdaQuery()
                .eq(User::getUserName,user.getUserName()));

        if(num>0){
            return -1;
        }
        user.setId(UUIDUtils.getGUID32());
        try {
            user.setAge((int)((System.currentTimeMillis()- DateUtils.parseDate(user.getBirthday(),"yyyy-MM-dd").getTime())/(24*60*60*1000)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setUserNo(String.valueOf(user.getId()).substring(0,6));
        user.setCreateBy(user.getUserNo());
        user.setModifyBy(user.getUserNo());
        int count=userMapper.insertTs(user);
        return count;
    }


}
