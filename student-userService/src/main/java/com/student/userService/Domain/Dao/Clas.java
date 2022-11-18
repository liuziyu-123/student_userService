package com.student.userService.Dao;

import lombok.Data;

@Data
public class Clas {

    /**
     * 主键Id
     */
    private String id;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 创建人
     */
    private String createBy;
}
