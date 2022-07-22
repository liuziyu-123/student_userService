package com.student.userService.Service;


import com.student.userService.Dao.User;
import com.student.userService.Vo.UserVo;

import java.util.List;


public interface IUserService {

    /**
     * 获取用户列表
     * @param page  页数
     * @param pageSize  每页个数
     * @param tsVo
     * @return
     */
    List<User> getTsInfo(int page, int pageSize, UserVo tsVo);

    /**
     * 新增用户信息
     * @param user
     * @return
     */
    int insertTs(User user);

    User userLogin(String mobile, String password);
}
