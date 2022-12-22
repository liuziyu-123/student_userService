package com.student.userService.Controller.Class;

import com.github.pagehelper.PageHelper;
import com.student.userService.Domain.Dao.GradeDao;
import com.student.userService.Domain.Entry.ClasEntry;
import com.student.userService.Domain.Entry.GradeEntry;
import com.student.userService.Domain.Entry.UserEntry;
import com.student.userService.Service.ClassService;
import com.student.userService.Utils.ApiResult;
import com.student.userService.Utils.ErrorConstant;
import com.student.userService.Utils.LocalThread;
import com.student.userService.Utils.PagingResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/*

 */
@RestController
@RequestMapping("class")
public class ClassController {

    @Autowired
    private ClassService classService;

    /**
     * 新增班级和班主任
     *
     * @param gradeId     年级Id
     * @param gradeName     年级名称
     * @param headTeacherId 班主任的ID
     * @return
     */
    @PostMapping("insertClass")
    public ApiResult insertClass(@RequestParam String gradeId,
                                 @RequestParam String gradeName,
                                 @RequestParam String headTeacherId) {

        UserEntry user = LocalThread.get();
        if (user == null) {
            return ApiResult.fail(ErrorConstant.NO_GET_LOGIN);
        }
        if (StringUtils.isAnyBlank(gradeId,gradeName, headTeacherId)) {
            return ApiResult.fail(ErrorConstant.EMPTY, "参数不能为空");
        }

        int count = classService.insertClass(user.getUserNo(), gradeId,gradeName, headTeacherId);
        return ApiResult.success(count);
    }


    /**
     * 修改班级的班主任
     *
     * @param classId     班级Id
     * @param headTeacher 班主任的ID
     * @return
     */
    @PostMapping("updateHeadTeacher")
    public ApiResult updateHeadTeacher(String classId, String headTeacher) {


        UserEntry user = LocalThread.get();
        if (user == null) {
            return ApiResult.fail(ErrorConstant.NO_GET_LOGIN);
        }
        if (StringUtils.isAnyBlank(classId, headTeacher)) {
            return ApiResult.fail(ErrorConstant.EMPTY, "参数不能为空");
        }

        int count = classService.updateHeadTeacher(classId, headTeacher);
        return ApiResult.success(count);
    }


    /**
     * 查询班级信息
     *
     * @param className       班级名称
     * @param headTeacherName 班主任的ID
     * @return
     */
    @GetMapping("selectAllClass")
    public ApiResult selectAllClass(String className, String headTeacherName, int page, int pageSize) {

        if (pageSize > 0) {
            PageHelper.startPage(page, pageSize);
        }
        List<ClasEntry> classList = classService.selectAllClass(className, headTeacherName);
        return ApiResult.success(new PagingResult<>(classList));
    }


    /**
     * 删除班级信息
     *
     * @param classId 班级Id
     * @return
     */
    @PostMapping("deleteClass")
    public ApiResult deleteClass(String classId) {

        if (StringUtils.isBlank(classId)) {
            return ApiResult.fail(ErrorConstant.EMPTY, "参数不能为空");
        }

        int count = classService.deleteClass(classId);
        return ApiResult.success(count);
    }


    /**
     * 禁用班级
     *
     * @param classId 班级Id
     * @return
     */
    @PostMapping("updateStatus")
    public ApiResult updateStatus(@RequestParam String classId, @RequestParam int status) {

        if (StringUtils.isBlank(classId)) {
            return ApiResult.fail(ErrorConstant.EMPTY, "参数不能为空");
        }

        int count = classService.updateStatus(classId, status);
        return ApiResult.success(count);
    }


}
