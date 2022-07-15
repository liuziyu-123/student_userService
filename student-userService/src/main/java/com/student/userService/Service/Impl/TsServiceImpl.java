package com.student.userService.Service.Impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.student.userService.Dao.User;
import com.student.userService.Mapper.TsMapper;
import com.student.userService.Service.TsService;
import com.student.userService.Utils.UUIDUtils;
import com.student.userService.Vo.TsVo;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.ParseException;
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
        if(pageSize>0){
            PageHelper.startPage(page,pageSize);
        }
        List<User> userList=tsMapper.selectList(Wrappers.<User>lambdaQuery()
        .eq(User::getUserName,tsVo.getTsName())
        .eq(User::getUserNo,tsVo.getTsNo())
        .eq(User::getIdentity,tsVo.getIdentity())
        .eq(User::getProvince,tsVo.getProvince())
        .eq(User::getCity,tsVo.getCity())
        .eq(User::getArea,tsVo.getArea()));
        return userList;
    }

    @Override
    public int insertTs(@RequestBody User user) {
        Long num=tsMapper.selectCount(Wrappers.<User>lambdaQuery()
        .eq(User::getUserName,user.getUserName()));

        if(num>0){
            return -1;
        }
        user.setId(UUIDUtils.getGUID32());
        try {
            user.setAge((int)((System.currentTimeMillis()- DateUtils.parseDate(user.getBirthday(),"yyyy-MM-dd").getTime())/(24*60*60*1000)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setUserNo(String.valueOf(user.getId()).substring(0,6));
        user.setCreateBy(user.getUserNo());
        user.setModifyBy(user.getUserNo());
        int count=tsMapper.insertTs(user);
        return count;
    }

    @Override
    public int outEx(String massage){

        try{
            int i=3/0;
        }catch(Exception e){
            System.out.println("子函数{}"+e);
            //
          //  throw e;
        }
        return 0;
    }
}
