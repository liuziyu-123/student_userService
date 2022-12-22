package com.student.userService.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.student.userService.Domain.Dao.ClassDao;
import com.student.userService.Domain.Entry.ClasEntry;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassMapper extends BaseMapper<ClasEntry> {

    List<ClasEntry> selectAllClass(String className, String headTeacherName);

    List<ClassDao> getClassByGradeId(String gradeId);
}
