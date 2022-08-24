package com.student.userService.Service.Impl;

import com.student.userService.Dao.Subject;
import com.student.userService.Mapper.SubjectMapper;
import com.student.userService.Service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImple implements SubjectService {

    @Autowired
    private SubjectMapper subjectMapper;

    @Override
    public int insertSubject(Subject subject) {
        if(subject==null){
            return 0;
        }
        int count=subjectMapper.insert(subject);
        return count;
    }

    @Override
    public List<Subject> getSubjectList(String subjectName) {
        List<Subject> subjects=  subjectMapper.getSubjectList(subjectName);
        return subjects;
    }
}
