package com.student.userService.Controller;

import com.student.userService.Domain.Entry.UserEntry;
import com.student.userService.Service.IUserService;
import com.student.userService.Utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rx.internal.util.LinkedArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlSchema;
import java.io.File;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserClient userClient;


    @PostMapping("login")
    public ApiResult login(@RequestBody UserEntry data) {
        UserEntry user = userService.userLogin(data.getMobile(), data.getPassword());


        if (user != null) {  //登录成功
            String token = JwtHelper.createToken(user.getId(), user.getPassword(), user.getUserNo());

            redisUtil.set(token, user, 30L, TimeUnit.MINUTES);
            return ApiResult.success(token);
        }
        return ApiResult.fail(-200, "登录失败");
    }

    @PostMapping("loginOut")
    public ApiResult loginOut(HttpServletRequest request) {
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

//
//        Map map=new LinkedList();
//        map.put("323","34343");
        }
        return ApiResult.success(sb);
    }

    @GetMapping("first")
    public ApiResult first() {
        File file1 = new File("H:\\3D建模\\16K WAV格式");
        File[] files=new File[3];
        for(int i=0 ;i<3;i++){
            files[i]=new File(file1.list()[i]);
        }
        Map<String,Object> map=new HashMap<>();
        map.put("accessToken","123456");
        map.put("groupId","11111");
        map.put("myfiles",files);
        Object obj=userClient.enrol(map);
        System.out.println(obj);
        return ApiResult.success();
    }
}