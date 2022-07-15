package com.student.userService.Controller.ts;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.student.userService.Dao.User;
import com.student.userService.Service.TsService;
import com.student.userService.Utils.ApiResult;
import com.student.userService.Utils.ErrorConstant;
import com.student.userService.Vo.TsVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("ts")
public class TsController {

    @Autowired
    private TsService tsService;

    /**
     * 获取师生列表
     * @param page   页码
     * @param pageSize   每页个数
     * @param tsVoData   查询数据
     * @return
     */
    @GetMapping("tsInfo")
    public ApiResult getTsInfo(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize,
                               @RequestParam String tsVoData){

        TsVo tsVo=new TsVo();
        if(!StringUtils.isBlank(tsVoData)){
            tsVo= JSON.parseObject(tsVoData, TsVo.class);
        }
        System.out.println("ts=="+ tsVo  );
        List<User> userList=tsService.getTsInfo(page,pageSize,tsVo);
        return ApiResult.success(userList);
    }

    @PostMapping("insertTs")
    public ApiResult insertTs(@RequestBody User user){
        if(user==null){
            return ApiResult.fail(ErrorConstant.EMPTY);
        }
        int count = tsService.insertTs(user);
        return ApiResult.success(count);
    }

    @PostMapping("outEx")
    public ApiResult outEx(String massage){

       try {
           tsService.outEx(massage);
       }catch (Exception e){
           System.out.println("出发了");
           e.printStackTrace();
       }
        return ApiResult.success(massage);
    }


}
