package com.student.userService.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.student.userService.Domain.Entry.RegionEntry;
import com.student.userService.Domain.Entry.UserEntry;
import com.student.userService.Domain.Vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TsMapper extends BaseMapper<UserEntry> {

    List<UserEntry> getUserList(UserVo userVo);

    List<RegionEntry> getProvince();

    List<RegionEntry> getCity(String provinceName);


    List<RegionEntry> getArea(String cityName);

}
