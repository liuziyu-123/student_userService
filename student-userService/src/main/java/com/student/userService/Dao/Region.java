package com.student.userService.Dao;

import lombok.Data;

@Data
//省市区的编码
public class Region {

    /**
     * 主键ID
     */
    private int id;

    /**
     * 行政区划代码
     */
    private int code;

    /**
     * 名称
     */
    private String name;

    /**
     * 上级id
     */
    private int parent_id;

    /**
     * 等级
     */
    private String level;
}
