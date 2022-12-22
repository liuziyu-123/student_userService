package com.student.userService.Domain.Entry;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 1.@Description:
 * 2.@author:liuziyu
 * 3.@Time:2022/12/22
 **/
@TableName("grade_subject")
@Data
public class GradeSubjectEntry {

    private String id;

    private String gradeId;

    private String  subjectId;


}
