package com.student.userService.Controller.ts;

import com.student.userService.Dao.User;
import com.student.userService.Service.TsService;
import com.student.userService.Utils.ApiResult;
import com.student.userService.Vo.TsVo;
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
     * @param tsVo
     * @return
     */
    @GetMapping("tsInfo")
    public ApiResult getTsInfo(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize,
                               @RequestBody TsVo tsVo){
        List<User> userList=tsService.getTsInfo(page,pageSize,tsVo);
        return ApiResult.success(userList);
    }
}
