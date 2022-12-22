package com.student.userService.Domain.Entry;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 *
 */
@Data
@TableName("region")
public class RegionEntry {

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
