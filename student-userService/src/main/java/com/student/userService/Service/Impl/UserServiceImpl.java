package com.student.userService.Service.Impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.student.userService.Dao.Region;
import com.student.userService.Dao.User;
import com.student.userService.Mapper.UserMapper;
import com.student.userService.Service.IUserService;
import com.student.userService.Utils.UUIDUtils;
import com.student.userService.Vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Override
    public List<Region> getProvince() {
        List<Region> regionList= userMapper.getProvince();
        return regionList;
    }

    @Override
    public List<Region> getCity(String provinceName) {
        List<Region> regionList= userMapper.getCity(provinceName);
        return regionList;
    }

    @Override
    public List<Region> getArea(String cityName) {
        List<Region> regionList= userMapper.getArea(cityName);
        return regionList;
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
        user.setAge(Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date()))- Integer.parseInt(user.getBirthday().substring(0,4)));
        user.setUserNo(String.valueOf(user.getId()).substring(0,6));
        user.setCreateBy(user.getUserNo());
        user.setModifyBy(user.getUserNo());
        int count=userMapper.insertTs(user);
        return count;
    }


}
