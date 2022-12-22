package com.student.userService.Service;


import com.student.userService.Domain.Entry.CourseEntry;

import java.util.List;

public interface CourseService {

    List<CourseEntry> getCourse(String courseName);

    int insertCourse(CourseEntry courseEntry);

}
