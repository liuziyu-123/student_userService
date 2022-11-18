package com.student.userService.Dao;

import lombok.Data;

import java.util.Date;

@Data
public class ResourcesFile {
    private String id;

    private String localUrl;

    private int onShelf;

    private Long size;

    private String mediaId;

    private String type;

    private String createBy;

    private Date  createTime;
}
