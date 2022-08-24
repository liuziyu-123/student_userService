package com.student.userService.Controller.Subject;

import com.github.pagehelper.PageHelper;
import com.student.userService.Dao.Subject;
import com.student.userService.Dao.User;
import com.student.userService.Service.SubjectService;
import com.student.userService.Utils.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    /**
     * 新增学科
     * post参数中   @RequestParam   可加可不加
     * @return
     */
    @PostMapping("insertSubject")
    public ApiResult insertSubject(String subjectName,int iseffective){
        User user = LocalThread.get();
        if(user == null){
            return ApiResult.fail(ErrorConstant.NO_GET_LOGIN);
        }
        if(StringUtils.isBlank(subjectName)){
            return ApiResult.fail(ErrorConstant.EMPTY);
        }
        Subject subject=new Subject();
        subject.setId(UUIDUtils.getGUID32());
        subject.setSubjectName(subjectName);
        subject.setCreateBy(user.getUserNo());
        subject.setIsEffective(iseffective);

        int count=subjectService.insertSubject(subject);
        return ApiResult.success(count);
    }

    /**
     * 查询学科信息
     * @return
     */
    @GetMapping("getSubjectList")
    public ApiResult getSubjectList(@RequestParam(required = false,defaultValue = "1") int page,
                                    @RequestParam(required = false,defaultValue = "10") int pageSize,
                                    @RequestParam(required = false) String  subjectName){


        if(pageSize>0){
            PageHelper.startPage(page,pageSize);
        }
       List<Subject> subjectList= subjectService.getSubjectList(subjectName);

       return ApiResult.success(new PagingResult<>(subjectList));
    }





}
