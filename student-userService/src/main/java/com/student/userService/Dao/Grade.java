package com.student.userService.Dao;

import lombok.Data;

@Data
public class Grade {

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

}
