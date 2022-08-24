package com.student.userService.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.student.userService.Dao.Course;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface CourseMapper extends BaseMapper<Course> {

    List<Course> selectCourseByName(String courseName);
}
