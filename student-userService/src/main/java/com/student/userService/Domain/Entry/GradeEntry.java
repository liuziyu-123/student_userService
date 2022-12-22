package com.student.userService.Domain.Entry;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("grade")
public class GradeEntry {

    /**
     * 主键Id
     */
    private String id;

    /**
     * 年级名称
     */
    private String gradeName;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 创建人
     */
    private String createBy;


    /**
     * 年级主任
     */
    private String gradeDirectorId;


}
