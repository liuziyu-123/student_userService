package com.student.userService.Service;

import com.student.userService.Dao.Subject;

import java.util.List;

public interface SubjectService {

    int insertSubject(Subject subject);

    List<Subject> getSubjectList(String subjectName);

}
