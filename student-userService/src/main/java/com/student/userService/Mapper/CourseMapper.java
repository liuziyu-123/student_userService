package com.student.userService.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.student.userService.Domain.Entry.CourseEntry;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper extends BaseMapper<CourseEntry> {

    List<CourseEntry> selectCourseByName(String courseName);
}
