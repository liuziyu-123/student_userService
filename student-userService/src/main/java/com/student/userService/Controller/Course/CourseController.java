package com.student.userService.Controller.Course;

import com.github.pagehelper.PageHelper;
import com.student.userService.Domain.Entry.CourseEntry;
import com.student.userService.Domain.Entry.UserEntry;
import com.student.userService.Service.CourseService;
import com.student.userService.Utils.ApiResult;
import com.student.userService.Utils.ErrorConstant;
import com.student.userService.Utils.LocalThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("course")
public class CourseController {
    @Autowired
    CourseService courseService;

    /**
     * 通过课程名称模糊查询
     *
     * @param courseName
     * @return
     */
    @GetMapping("getCourse")
    public ApiResult getCourse(@RequestParam(required = false, defaultValue = "1") int page,
                               @RequestParam(required = false, defaultValue = "10") int pageSize,
                               @RequestParam(required = false) String courseName) {


        if (pageSize > 0) {
            PageHelper.startPage(page, pageSize);
        }
        List<CourseEntry> courseList = courseService.getCourse(courseName);
        return ApiResult.success(courseList);
    }

    /**
     * @return
     * @descriptions:新增课程
     * @author:刘梓毓
     * @Time:${DATE}
     */
    @PostMapping("insertCourse")
    public ApiResult insertCourse(@RequestBody CourseEntry courseEntry) {
        UserEntry user = LocalThread.get();
        if (user == null) {
            return ApiResult.fail(ErrorConstant.NO_GET_LOGIN);
        }
        if (courseEntry == null) {
            return ApiResult.fail(ErrorConstant.EMPTY);
        }
        courseEntry.setCreateBy(user.getUserNo());

        return  ApiResult.success(courseService.insertCourse(courseEntry));
    }
}
