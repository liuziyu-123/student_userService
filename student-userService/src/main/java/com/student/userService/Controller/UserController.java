package com.student.userService.Controller;


import com.student.userService.Dao.User;
import com.student.userService.Service.IUserService;
import com.student.userService.Utils.ApiResult;
import com.student.userService.Utils.JwtHelper;
import com.student.userService.Utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
            String token = JwtHelper.createToken(user.getId(),user.getPassword());

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

}
