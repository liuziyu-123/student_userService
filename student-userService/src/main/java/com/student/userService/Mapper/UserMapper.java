package com.student.userService.Mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.student.userService.Dao.Region;
import com.student.userService.Dao.User;
import com.student.userService.Vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    User userLogin(String mobile ,String password);

    int insertTs(User user);

    List<User> getUserList(UserVo userVo);

    List<Region> getProvince();

    List<Region> getCity(String provinceName);


    List<Region> getArea(String cityName);

}
