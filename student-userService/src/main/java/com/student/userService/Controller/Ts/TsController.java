package com.student.userService.Controller.Ts;

import com.github.pagehelper.PageHelper;
import com.student.userService.Config.PropotiesConfiger;
import com.student.userService.Domain.Entry.RegionEntry;
import com.student.userService.Domain.Entry.UserEntry;
import com.student.userService.Domain.Vo.UserVo;
import com.student.userService.Service.TsService;
import com.student.userService.Utils.ApiResult;
import com.student.userService.Utils.ErrorConstant;
import com.student.userService.Utils.LocalThread;
import com.student.userService.Utils.PagingResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("ts")
public class TsController {

    @Autowired
    private TsService tsService;

    @Autowired
    private PropotiesConfiger propotiesConfiger;
//
//
//    @Autowired
//    private Environment environment;
//
    @Value("${white.list.login}")
    private String login;


    /**
     * 获取用户列表
     *
     * @param userVo 查询数据
     * @return
     */
    @PostMapping("tsInfo")
    public ApiResult getTsInfo(@RequestBody UserVo userVo) {
        System.out.println(propotiesConfiger.getList());
        System.out.println(login);
        UserEntry userInfo = LocalThread.get();
        if (userInfo == null) {
            return ApiResult.fail(ErrorConstant.NO_GET_LOGIN, "用户不存在");
        }
        if (userVo.getPageSize() > 0) {
            PageHelper.startPage(userVo.getPage(), userVo.getPageSize());
        }
        List<UserEntry> userList = tsService.getTsInfo(userVo);

       // Set<String> list=new HashSet<>();
        return ApiResult.success(new PagingResult<>(userList));
    }


    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @PostMapping("insertTs")
    public ApiResult insertTs(@RequestBody UserEntry user) {
        if (user == null) {
            return ApiResult.fail(ErrorConstant.NO_GET_LOGIN);
        }
        int count = tsService.insertTs(user);
        return ApiResult.success(count);
    }


    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    @PostMapping("updateTs")
    public ApiResult updateTs(@RequestBody UserEntry user) {
        if (user == null) {
            return ApiResult.fail(ErrorConstant.NO_GET_LOGIN);
        }
        int count = tsService.updateTs(user);
        return ApiResult.success(count);
    }


    /**
     * 删除用户
     *
     * @param userId
     * @return
     */
    @PostMapping("deleteTs")
    public ApiResult deleteTs(@RequestParam String userId) {
        if (StringUtils.isBlank(userId)) {
            return ApiResult.fail(ErrorConstant.EMPTY);
        }
        int count = tsService.deleteUser(userId);
        return ApiResult.success(count);
    }

    /**
     * 获取全国所有的省
     *
     * @return
     */
    @GetMapping("getProvince")
    public ApiResult getProvince() {
        List<RegionEntry> regionList = tsService.getProvince();
        return ApiResult.success(regionList);
    }


    /**
     * 获取某个省的所有市
     *
     * @param provinceName 省份名称
     * @return
     */
    @GetMapping("getCity")
    public ApiResult getCity(String provinceName) {

        List<RegionEntry> regionList = tsService.getCity(provinceName);
        return ApiResult.success(regionList);
    }


    /**
     * 获取某个市的所有区
     *
     * @param cityName 市名
     * @return
     */
    @GetMapping("getArea")
    public ApiResult getArea(String cityName) {

        List<RegionEntry> regionList = tsService.getArea(cityName);
        return ApiResult.success(regionList);
    }


    /**
     * 获取所有教师信息(排除班主任)
     *
     * @param
     * @return
     */
    @GetMapping("getTeacherInfo")
    public ApiResult getTeacherInfo() {

        return ApiResult.success(tsService.getTeacherInfo());
    }


    @PostMapping("outEx")
    public ApiResult outEx(String massage) {

        try {
            tsService.outEx(massage);
        } catch (Exception e) {
            System.out.println("出发了");
            e.printStackTrace();
        }
        return ApiResult.success(massage);
    }


}
