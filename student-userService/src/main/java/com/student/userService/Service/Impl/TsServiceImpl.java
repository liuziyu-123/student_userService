package com.student.userService.Service.Impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.student.userService.Domain.Entry.RegionEntry;
import com.student.userService.Domain.Entry.UserEntry;
import com.student.userService.Domain.Vo.UserVo;
import com.student.userService.Mapper.TsMapper;
import com.student.userService.Service.TsService;
import com.student.userService.Utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TsServiceImpl implements TsService {

    @Autowired
    private TsMapper tsMapper;

    @Override
    public List<RegionEntry> getProvince() {
        List<RegionEntry> regionList = tsMapper.getProvince();
        return regionList;
    }

    @Override
    public List<RegionEntry> getCity(String provinceName) {
        List<RegionEntry> regionList = tsMapper.getCity(provinceName);
        return regionList;
    }

    @Override
    public List<RegionEntry> getArea(String cityName) {
        List<RegionEntry> regionList = tsMapper.getArea(cityName);
        return regionList;
    }

    @Override
    public List<UserEntry> getTeacherInfo() {
        return tsMapper.selectList(Wrappers.<UserEntry>lambdaQuery()
                .eq(UserEntry::getIdentity, 0)
                .eq(UserEntry::getIsHead, 0));
    }

    /**
     * 获取用户列表
     *
     * @param userVo
     * @return
     */
    @Override
    public List<UserEntry> getTsInfo(UserVo userVo) {

        List<UserEntry> userList = tsMapper.getUserList(userVo);
        return userList;
    }

    /**
     * 新增用户列表列表
     *
     * @param user
     * @return
     */
    @Override
    public int insertTs(UserEntry user) {
        Long num = tsMapper.selectCount(Wrappers.<UserEntry>lambdaQuery()
                .eq(UserEntry::getUserName, user.getUserName()));

        if (num > 0) {
            return -1;
        }
        user.setId(UUIDUtils.getGUID32());
        user.setAge(Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date())) - Integer.parseInt(user.getBirthday().substring(0, 4)));
        user.setUserNo(String.valueOf(user.getId()).substring(0, 6));
        user.setCreateBy(user.getUserNo());
        user.setModifyBy(user.getUserNo());
        return tsMapper.insert(user);
    }

    @Override
    public int updateTs(UserEntry user) {
        Long num = tsMapper.selectCount(Wrappers.<UserEntry>lambdaQuery()
                .eq(UserEntry::getUserName, user.getUserName()));

        if (num > 0) {
            return -1;
        }
        user.setModifyBy(user.getUserNo());
        return tsMapper.updateById(user);
    }

    @Override
    public int deleteUser(String userId) {
        return tsMapper.deleteById(userId);
    }


    @Override
    public int outEx(String massage) {

        try {
            int i = 3 / 0;
        } catch (Exception e) {
            System.out.println("子函数{}" + e);
            //
            throw e;
        }
        return 0;
    }
}
