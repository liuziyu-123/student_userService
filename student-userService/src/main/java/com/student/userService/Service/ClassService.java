package com.student.userService.Service;


import com.student.userService.Domain.Dao.GradeDao;
import com.student.userService.Domain.Entry.ClasEntry;
import com.student.userService.Domain.Entry.GradeEntry;

import java.util.List;

public interface ClassService {

    /**
     * 新增班级和班主任
     *
     * @param gradeId     年级id
     * @param gradeName     年级名称
     * @param headTeacherId 班主任的ID
     * @return
     */
    int insertClass(String userNo, String gradeId, String gradeName,String headTeacherId);

    int updateHeadTeacher(String classId, String headTeacher);

    List<ClasEntry> selectAllClass(String className, String headTeacherName);

    int deleteClass(String classId);

    int updateStatus(String classId, int status);


}
