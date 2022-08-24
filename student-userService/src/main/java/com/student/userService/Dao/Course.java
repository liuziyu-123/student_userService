package com.student.userService.Dao;

import lombok.Data;

import java.util.Date;

@Data
public class Course {

    /**
     * 主键Id
     */
    private String id;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 学科Id
     */
    private String subjectId;

    /**
     * 是否生效（0  失效   1 生效）
     */
    private int isEffective;


}
