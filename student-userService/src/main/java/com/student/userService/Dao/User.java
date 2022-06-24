package com.student.userService.Dao;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private String id;

    private String mobile;

    private String userName;

    private String password;

    private String province;

    private String city;

    private String area;

    private int age;

    private  int sex;

    private int identity;

    private int userNo;

    private Date brithday;

    private Date createTime;

    private String createBy;

    private String modifyBy;

    private Date modifyTime;


}
