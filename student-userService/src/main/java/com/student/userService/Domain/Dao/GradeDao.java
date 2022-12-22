package com.student.userService.Domain.Dao;

import lombok.Data;

/**
 * 1.@Description:负责年级信息的增和改
 * 2.@author:liuziyu
 * 3.@Time:2022/12/17
 **/
@Data
public class GradeDao {

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


    /**
     * 年级主任Id
     */
    private String gradeDirectorId;

    /**
     * 年级主任姓名
     */
    private String gradeDirctorName;
}
