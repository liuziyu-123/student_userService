package com.student.userService.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.student.userService.Dao.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TsMapper extends BaseMapper<User> {

    int insertTs(User user);
}
