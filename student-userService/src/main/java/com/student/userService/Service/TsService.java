package com.student.userService.Service;

import com.student.userService.Dao.User;
import com.student.userService.Vo.TsVo;

import java.text.ParseException;
import java.util.List;

/**
 * 系统管理-师生管理
 */
public interface TsService {

    /**
     * 获取师生列表
     * @param page  页数
     * @param pageSize  每页个数
     * @param tsVo
     * @return
     */
    List<User> getTsInfo(int page, int pageSize, TsVo tsVo);

    /**
     * 新增师生信息
     * @param user
     * @return
     */
    int insertTs(User user);

    int outEx(String massage);
}
