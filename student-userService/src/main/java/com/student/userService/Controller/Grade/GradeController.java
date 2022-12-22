package com.student.userService.Controller.Grade;

import com.github.pagehelper.PageHelper;
import com.student.userService.Domain.Dao.GradeDao;
import com.student.userService.Domain.Entry.GradeEntry;
import com.student.userService.Service.GradeService;
import com.student.userService.Utils.ApiResult;
import com.student.userService.Utils.ErrorConstant;
import com.student.userService.Utils.PagingResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 1.@Description:
 * 2.@author:liuziyu
 * 3.@Time:2022/12/3
 **/
@RestController
@RequestMapping("grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    /**
     * @return
     * @descriptions:查询年级
     * @author:liuziyu
     * @Time:2022-11-12
     */
    @GetMapping("selectGrade")
    public ApiResult selectGrade(String gradeName, String gradeDirectorName, int page, int pageSize) {
        if (pageSize > 0) {
            PageHelper.startPage(page, pageSize);
        }
        List<GradeDao> gradeList = gradeService.selectGrade(gradeName, gradeDirectorName);
        return ApiResult.success(new PagingResult<>(gradeList));
    }

    /**
     * @return
     * @descriptions:新增年级
     * @author:liuziyu
     * @Time:2022-11-12
     */
    @PostMapping("insertGrade")
    public ApiResult insertGrade(String gradeName, String gradeDirectorId) {

        if (StringUtils.isAnyBlank(gradeDirectorId, gradeName)) {
            return ApiResult.fail(ErrorConstant.EMPTY);
        }
        return ApiResult.success(gradeService.insertGrade(gradeName, gradeDirectorId));
    }

    /**
     * @return
     * @descriptions:修改年级信息
     * @author:liuziyu
     * @Time:2022-11-12
     */
    @PostMapping("updateGradeInfo")
    public ApiResult updateGradeInfo(@RequestBody GradeEntry gradeEntry) {

        if (gradeEntry == null) {
            return ApiResult.fail(ErrorConstant.EMPTY);
        }
        return ApiResult.success(gradeService.updateGradeInfo(gradeEntry));
    }


}
