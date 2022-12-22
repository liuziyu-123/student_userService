package com.student.userService.Service.Impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.student.userService.Domain.Dao.GradeDao;
import com.student.userService.Domain.Entry.ClasEntry;
import com.student.userService.Domain.Entry.GradeEntry;
import com.student.userService.Domain.Entry.UserEntry;
import com.student.userService.Mapper.ClassMapper;
import com.student.userService.Mapper.GradeMapper;
import com.student.userService.Mapper.UserMapper;
import com.student.userService.Service.GradeService;
import com.student.userService.Utils.LocalThread;
import com.student.userService.Utils.MyException;
import com.student.userService.Utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 1.@Description:
 * 2.@author:liuziyu
 * 3.@Time:2022/12/17
 **/
@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeMapper gradeMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ClassMapper classMapper;



    @Override
    public int insertGrade(String gradeName, String gradeDirectorId) {
        UserEntry user = LocalThread.get();
        long count = gradeMapper.selectCount(Wrappers.<GradeEntry>lambdaQuery()
                .eq(GradeEntry::getGradeName, gradeName));
        if (count > 0) {
            return 0;
        }
        GradeEntry gradeEntry = new GradeEntry();
        gradeEntry.setId(UUIDUtils.getGUID32());
        gradeEntry.setCreateBy(user.getUserNo());
        gradeEntry.setGradeName(gradeName);
        gradeEntry.setGradeDirectorId(gradeDirectorId);


        return gradeMapper.insert(gradeEntry);
    }

    @Override
    public int updateGradeInfo(GradeEntry gradeEntry) {
//        Long count = classMapper.selectCount(Wrappers.<ClasEntry>lambdaQuery()
//                .eq(ClasEntry::getGradeId, gradeEntry.getId()));
//        if(count>0){
//            throw new MyException("该年级下有班级")
//        }
        return gradeMapper.updateById(gradeEntry);
    }

    @Override
    public List<GradeDao> selectGrade(String gradeName, String gradeDirectorId) {
        List<GradeDao> gradeDaoList=  gradeMapper.selectGrade(gradeName, gradeDirectorId);
        for(GradeDao gradeDao:gradeDaoList){
            gradeDao.setGradeDirctorName(userMapper.getUserName(gradeDao.getGradeDirectorId()));
        }
        return gradeDaoList;
    }
}
