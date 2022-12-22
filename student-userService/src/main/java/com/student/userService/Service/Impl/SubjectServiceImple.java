package com.student.userService.Service.Impl;

import com.student.userService.Domain.Entry.SubjectEntry;
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
    public int insertSubject(SubjectEntry subject) {
        if (subject == null) {
            return 0;
        }
        int count = subjectMapper.insert(subject);
        return count;
    }

    @Override
    public List<SubjectEntry> getSubjectList(String subjectName) {
        List<SubjectEntry> subjects = subjectMapper.getSubjectList(subjectName);
        return subjects;
    }

    @Override
    public int updateSubject(SubjectEntry subject) {
        int count = subjectMapper.updateById(subject);
        return count;
    }

    @Override
    public int deleteSubject(List<String> subjectIds) {
        int count = subjectMapper.deleteBatchIds(subjectIds);
        return count;
    }
}
