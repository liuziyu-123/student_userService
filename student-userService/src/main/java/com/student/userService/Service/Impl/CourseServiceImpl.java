package com.student.userService.Service.Impl;

import com.student.userService.Domain.Entry.CourseEntry;
import com.student.userService.Mapper.CourseMapper;
import com.student.userService.Service.CourseService;
import com.student.userService.Utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<CourseEntry> getCourse(String courseName) {

        return courseMapper.selectCourseByName(courseName);
    }

    @Override
    public int insertCourse(CourseEntry courseEntry) {
        courseEntry.setId(UUIDUtils.getGUID32());
        return courseMapper.insert(courseEntry);
    }
}
