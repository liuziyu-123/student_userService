package com.student.userService.Dao;

import lombok.Data;

@Data
public class Subject {

    /**
     * 主键Id
     */
    private String id;

    /**
     * 学科名称
     */
    private String subjectName;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 是否生效   0 失效    1 生效
     */
    private int isEffective;
}
