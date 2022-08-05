package com.student.userService.Service;


import com.student.userService.Dao.Region;
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

    /**
     * 用户登录
     * @param mobile  手机号码
     * @param password  密码
     * @return
     */
    User userLogin(String mobile, String password);

    /**
     * 获取所有的省份
     * @return
     */
    List<Region> getProvince();

    /**
     * 获取某个省的所有市
     * @return
     */
    List<Region> getCity(String provinceId);


    /**
     * 获取某个市的所有区
     * @return
     */
    List<Region> getArea(String cityId);
}
