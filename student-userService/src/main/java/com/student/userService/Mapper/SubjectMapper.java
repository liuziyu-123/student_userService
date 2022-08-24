package com.student.userService.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.student.userService.Dao.Subject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SubjectMapper extends BaseMapper<Subject> {

    List<Subject> getSubjectList(String subjectName);
}
