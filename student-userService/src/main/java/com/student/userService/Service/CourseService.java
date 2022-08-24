package com.student.userService.Service;


import com.student.userService.Dao.Course;

import java.util.List;

public interface CourseService {

    List<Course> getCourse(String courseName);

    int insertCourse(Course course);

}
