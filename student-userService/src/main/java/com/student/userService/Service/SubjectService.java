package com.student.userService.Service;

import com.student.userService.Domain.Entry.SubjectEntry;

import java.util.List;

public interface SubjectService {

    int insertSubject(SubjectEntry subject);

    List<SubjectEntry> getSubjectList(String subjectName);

    int updateSubject(SubjectEntry subject);

    int deleteSubject(List<String> subjectIds);

}
