package com.student.userService.Service.Impl;

import com.student.userService.Dao.User;
import com.student.userService.Mapper.TsMapper;
import com.student.userService.Service.TsService;
import com.student.userService.Vo.TsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TsServiceImpl implements TsService {

    @Autowired
    private TsMapper tsMapper;
    @Override
    public List<User> getTsInfo(int page, int pageSize, TsVo tsVo) {

        if(page==0 && pageSize==0){
            return null;
        }

        System.out.println(page+pageSize);

        return null;
    }

    @Override
    public int insertTs(User user) {
        int count=tsMapper.insertTs(user);
        return 0;
    }
}
