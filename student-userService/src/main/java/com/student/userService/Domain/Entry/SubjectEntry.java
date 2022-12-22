package com.student.userService.Domain.Entry;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("subject")
public class SubjectEntry {

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
