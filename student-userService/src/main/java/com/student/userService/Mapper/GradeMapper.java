package com.student.userService.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.student.userService.Domain.Dao.GradeDao;
import com.student.userService.Domain.Entry.GradeEntry;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 1.@Description:
 * 2.@author:liuziyu
 * 3.@Time:2022/11/12
 **/
@Mapper
public interface GradeMapper extends BaseMapper<GradeEntry> {
    List<GradeDao> selectGrade(String gradeName, String gradeDirectorId);

}
