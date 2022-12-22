package com.student.userService.Domain.Entry;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user")
public class UserEntry {

    @TableId
    private String id;

    private String mobile;

    private String userName;

    private String password;

    private String provinceName;

    private String cityName;

    private String areaName;

    private int age;

    private int sex;

    private int identity;

    private String userNo;

    private String birthday;

    private Date createTime;

    private String createBy;

    private String modifyBy;

    private Date modifyTime;

    private int isHead;


}
