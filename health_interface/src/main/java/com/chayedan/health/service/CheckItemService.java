package com.chayedan.health.service;

import com.chayedan.health.entity.PageResult;
import com.chayedan.health.entity.QueryPageBean;
import com.chayedan.health.pojo.CheckItem;

/**
 * @author chayedan666
 * @version 1.0
 * @className: CheckItemService
 * @description: 检查项业务接口
 * @date: 2020/4/15
 */
public interface CheckItemService {
    void add(CheckItem checkItem);

    /**
     * 分页查询检查项列表
     * @param queryPageBean
     * @return
     */
    PageResult pageQuery(QueryPageBean queryPageBean);

    /**
     * 基于ID删除检查项
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据id返回信息
     * @param id
     * @return
     */
    CheckItem findById(Integer id);

    /**
     * 编辑项目
     * @param checkItem
     */
    public void edit(CheckItem checkItem);
}
