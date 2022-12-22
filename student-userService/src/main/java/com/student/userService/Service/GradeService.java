package com.student.userService.Service;

import com.student.userService.Domain.Dao.GradeDao;
import com.student.userService.Domain.Entry.GradeEntry;

import java.util.List;

/**
 * 1.@Description:
 * 2.@author:liuziyu
 * 3.@Time:2022/12/17
 **/
public interface GradeService {

    /**
     * 新增年级和年级主任
     *
     * @param gradeName       年级
     * @param gradeDirectorId 年级主任的ID
     * @return
     */
    int insertGrade(String gradeName, String gradeDirectorId);

    int updateGradeInfo(GradeEntry gradeEntry);

    List<GradeDao> selectGrade(String gradeName, String gradeDirectorId);
}
