package com.student.userService.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.student.userService.Domain.Entry.SubjectEntry;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SubjectMapper extends BaseMapper<SubjectEntry> {

    List<SubjectEntry> getSubjectList(String subjectName);
}
