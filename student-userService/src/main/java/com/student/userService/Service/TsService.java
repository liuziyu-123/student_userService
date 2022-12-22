package com.student.userService.Service;

import com.student.userService.Domain.Entry.RegionEntry;
import com.student.userService.Domain.Entry.UserEntry;
import com.student.userService.Domain.Vo.UserVo;

import java.util.List;

/**
 * 系统管理-师生管理
 */
public interface TsService {

    /**
     * 获取用户列表
     *
     * @param tsVo
     * @return
     */
    List<UserEntry> getTsInfo(UserVo tsVo);

    /**
     * 新增用户信息
     *
     * @param user
     * @return
     */
    int insertTs(UserEntry user);

    int updateTs(UserEntry user);

    int deleteUser(String userId);

    /**
     * 获取所有的省份
     *
     * @return
     */
    List<RegionEntry> getProvince();

    /**
     * 获取某个省的所有市
     *
     * @return
     */
    List<RegionEntry> getCity(String provinceName);


    /**
     * 获取某个市的所有区
     *
     * @return
     */
    List<RegionEntry> getArea(String cityName);

    List<UserEntry> getTeacherInfo();

    int outEx(String massage);
}
