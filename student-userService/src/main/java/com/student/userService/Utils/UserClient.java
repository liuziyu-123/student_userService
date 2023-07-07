package com.student.userService.Utils;

/**
 * 1.@Description:
 * 2.@author:liuziyu
 * 3.@Time:2023/5/17
 **/

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.util.Map;


@FeignClient(name = "wav" ,url="http://47.108.228.186:8899")
public interface UserClient {
    @PostMapping("wav/enrol")
    Object enrol(@RequestBody Map<String,Object> map);

    @PostMapping("wav/verify")
    Object verify(@RequestParam("accessToken") String token,
                 @RequestParam("myfiles") File[] files);
}

