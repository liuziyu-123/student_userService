package com.student.userService.Service.Impl;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.student.userService.Domain.Dao.GradeDao;
import com.student.userService.Domain.Entry.ClasEntry;
import com.student.userService.Domain.Entry.GradeEntry;
import com.student.userService.Domain.Entry.UserEntry;
import com.student.userService.Mapper.ClassMapper;
import com.student.userService.Mapper.GradeMapper;
import com.student.userService.Mapper.UserMapper;
import com.student.userService.Service.ClassService;
import com.student.userService.Utils.LocalThread;
import com.student.userService.Utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClassServiceImpl implements ClassService {


    @Autowired
    private ClassMapper classMapper;

    /**
     * 新增班级和班主任
     *
     * @param gradeId     班级
     * @param headTeacherId 班主任的ID
     * @return
     */
    @Override
    public int insertClass(String userNo, String gradeId,String gradeName, String headTeacherId) {

        Long count = classMapper.selectCount(Wrappers.<ClasEntry>lambdaQuery()
                .eq(ClasEntry::getGradeId, gradeId));

        ClasEntry clasEntry = new ClasEntry();
        clasEntry.setId(UUIDUtils.getGUID32());
        clasEntry.setClassNo(count.intValue()+1);
        clasEntry.setCreateBy(userNo);
        clasEntry.setGradeId(gradeId);
        clasEntry.setHeadTeacherId(headTeacherId);
        clasEntry.setClassName(gradeName+count.intValue()+1+"班");
        return classMapper.insert(clasEntry);
    }

    /**
     * 修改班级的班主任
     *
     * @param classId     班级Id
     * @param headTeacher 班主任的ID
     * @return
     */
    @Override
    public int updateHeadTeacher(String classId, String headTeacher) {
        return classMapper.update(null, Wrappers.<ClasEntry>lambdaUpdate()
                .eq(ClasEntry::getId, classId)
                .set(ClasEntry::getHeadTeacherId, headTeacher));
    }

    @Override
    public List<ClasEntry> selectAllClass(String className, String headTeacherName) {
        return classMapper.selectAllClass(className, headTeacherName);
    }

    @Override
    public int deleteClass(String classId) {
        return classMapper.deleteById(classId);
    }

    @Override
    public int updateStatus(String classId, int status) {
        return classMapper.update(null, Wrappers.<ClasEntry>lambdaUpdate()
                .set(ClasEntry::getIsForbidden, status)
                .eq(ClasEntry::getId, classId));
    }


}
