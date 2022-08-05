package com.student.userService.Controller;

import com.alibaba.fastjson.JSONObject;
import com.student.userService.Dao.Region;
import com.student.userService.Dao.User;
import com.student.userService.Service.IUserService;
import com.student.userService.Utils.*;
import com.student.userService.Vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private  RedisUtil redisUtil;

    @PostMapping("login")
    public ApiResult login(@RequestBody  User data){
      User user=userService.userLogin(data.getMobile(),data.getPassword());


        if(user!=null){  //登录成功
            String token = JwtHelper.createToken(user.getId(),user.getPassword(),user.getUserNo());

            redisUtil.set(token,user,30L, TimeUnit.MINUTES);
            return ApiResult.success(token);
        }
        return ApiResult.fail( -200,"登录失败");
    }

    @PostMapping("loginOut")
    public ApiResult loginOut(HttpServletRequest request){
        String token = request.getHeader("token");
        //删除redis 中的token
        redisUtil.remove(token);
        return ApiResult.success("成功退出");
    }

    @GetMapping("verification")
    public ApiResult verification() {
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghigklmnopqrstuvwxyz0123456789";
        Random ran = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 4; i++) {
            int index = ran.nextInt(str.length());
            char ch = str.charAt(index);
            sb.append(ch);
        }
        return ApiResult.success(sb);
    }

    /**
     * 获取用户列表
     * @param page   页码
     * @param pageSize   每页个数
     * @param tsVoData   查询数据
     * @return
     */
    @GetMapping("tsInfo")
    public ApiResult getTsInfo(@RequestParam(required = false,defaultValue = "1") int page,
                               @RequestParam(required = false,defaultValue = "10") int pageSize,
                               @RequestParam String tsVoData){

        User userInfo= LocalThread.get();
        if(userInfo==null){
            return ApiResult.fail(ErrorConstant.NO_GET_LOGIN,"用户不存在");
        }
        UserVo tsVo= JSONObject.parseObject(tsVoData,UserVo.class);
        List<User> userList=userService.getTsInfo(page,pageSize,tsVo);
        return ApiResult.success(userList);
    }

    @PostMapping("insertTs")
    public ApiResult insertTs(@RequestBody User user){
        if(user==null){
            return ApiResult.fail(ErrorConstant.EMPTY);
        }
        int count = userService.insertTs(user);
        return ApiResult.success(count);
    }

    /**
     * 获取全国所有的省
     * @return
     */
    @GetMapping("getProvince")
    public ApiResult getProvince(){
        User user=LocalThread.get();
        if(user==null){
            return ApiResult.fail(ErrorConstant.EMPTY);
        }

        List<Region> regionList=userService.getProvince();
        return ApiResult.success(regionList);
    }



    /**
     * 获取某个省的所有市
     * @param provinceName  省份名称
     * @return
     */
    @GetMapping("getCity")
    public ApiResult getCity(String provinceName){
        User user=LocalThread.get();
        if(user==null){
            return ApiResult.fail(ErrorConstant.EMPTY);
        }

        List<Region> regionList=userService.getCity(provinceName);
        return ApiResult.success(regionList);
    }


    /**
     * 获取某个市的所有区
     * @param cityId  省份Id
     * @return
     */
    @GetMapping("getArea")
    public ApiResult getArea(String cityId){
        User user=LocalThread.get();
        if(user==null){
            return ApiResult.fail(ErrorConstant.EMPTY);
        }

        List<Region> regionList=userService.getArea(cityId);
        return ApiResult.success(regionList);
    }

}
