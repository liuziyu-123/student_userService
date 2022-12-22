package com.student.userService.Domain.Vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class UserVo {

    @TableField(exist = false)
    private int page;

    @TableField(exist = false)
    private int pageSize;

    private String userName;

    private String userNo;

    private String identity;

    private String provinceName;

    private String cityName;

    private String areaName;

}
