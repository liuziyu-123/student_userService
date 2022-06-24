package com.student.userService.Service;

import com.student.userService.Dao.User;
import com.student.userService.Vo.TsVo;

import java.util.List;

/**
 * 系统管理-师生管理
 */
public interface TsService {

    List<User> getTsInfo(int page, int pageSize, TsVo tsVo);
}
