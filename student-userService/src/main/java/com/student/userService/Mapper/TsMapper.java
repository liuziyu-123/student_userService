package com.student.userService.Mapper;

import com.student.userService.Dao.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TsMapper {

    int insertTs(User user);
}
