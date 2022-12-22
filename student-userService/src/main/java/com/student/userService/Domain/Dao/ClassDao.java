package com.student.userService.Domain.Dao;

import lombok.Data;

/**
 * 1.@Description:
 * 2.@author:liuziyu
 * 3.@Time:2022/12/17
 **/
@Data
public class ClassDao {

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
     * 创建人
     */
    private String createName;




    /**
     * 是否禁用 （0 不禁用   1 禁用）
     */
    private int isForbidden;


    /**
     * 班级序号
     */
    private int classNo;
}
