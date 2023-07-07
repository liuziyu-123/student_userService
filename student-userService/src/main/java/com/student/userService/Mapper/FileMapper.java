package com.student.userService.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.student.userService.Domain.Entry.ResourcesFileEntry;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 1.@Description:
 * 2.@author:liuziyu
 * 3.@Time:2023/1/10
 **/
@Mapper
public interface FileMapper extends BaseMapper<ResourcesFileEntry> {

    List<ResourcesFileEntry>  getList(int type);
}
