package com.student.userService.Utils;

import java.util.List;

public class PageResult {

    /**
     * 当前页
     */
    public int curPage;

    /**
     * 每页数量
     */
    public int pageSize;
    /**
     * 总数量
     */
    public int totalCount;
    /**
     * 总页数
     */
    public int totalPages;

    /**
     * 返回集合
     */
    public List<T> list;
}
