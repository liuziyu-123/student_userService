package com.student.userService.Domain.Entry;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("clas")
public class ClasEntry {

    /**
     * 主键Id
     */
    private String id;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 班主任Id
     */
    private String headTeacherId;

    /**
     * 班主任姓名
     */
    private String headTeacherName;


    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 创建人
     */
    private String createBy;


    /**
     * 是否禁用 （0 不禁用   1 禁用）
     */
    private int isForbidden;


    /**
     * 班级序号
     */
    private int classNo;

    /**
     * 年级Id
     */
    private String gradeId;
}
