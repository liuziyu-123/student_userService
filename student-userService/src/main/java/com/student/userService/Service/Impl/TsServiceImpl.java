package com.student.userService.Service.Impl;

import com.student.userService.Mapper.TsMapper;
import com.student.userService.Service.TsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TsServiceImpl implements TsService {

    @Override
    public int outEx(String massage){

        try{
            int i=3/0;
        }catch(Exception e){
            System.out.println("子函数{}"+e);
            //
          //  throw e;
        }
        return 0;
    }
}
