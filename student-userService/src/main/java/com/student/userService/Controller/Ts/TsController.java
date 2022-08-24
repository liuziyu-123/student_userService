package com.student.userService.Controller.Ts;

import com.student.userService.Service.TsService;
import com.student.userService.Utils.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ts")
public class TsController {

    @Autowired
    private TsService tsService;



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
