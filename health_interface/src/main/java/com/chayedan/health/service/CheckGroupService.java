package com.chayedan.health.service;

import com.chayedan.health.entity.PageResult;
import com.chayedan.health.pojo.CheckGroup;

import java.util.List;

/**
 * @author chayedan666
 * @version 1.0
 * @className: CheckGroupService
 * @description: 检查组业务接口
 * @date: 2020/4/15
 */
public interface CheckGroupService {
    /**
     * 新增检查组
     * @param checkGroup 检查组表单数据
     * @param checkItemIds 检查项ID数据
     */
    void add(CheckGroup checkGroup,Integer[] checkItemIds);

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @param queryString
     * @return
     */
    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    /**
     * 根据ID，获取检查组对象
     * @param id
     * @return
     */
    CheckGroup findById(Integer id);
    /**
     * 基于检查组ID获取检查项ID列表
     * @param id
     * @return
     */
    List<Integer> findCheckItemsByCheckGroupId(Integer id);

    /**
     * 编辑检查组
     * @param checkGroup 检查基本数据
     * @param checkItemIds 检查项选择列表
     */
    void edit(CheckGroup checkGroup,Integer[] checkItemIds);

    /**
     * 查询所有检查组
     * @return
     */
    List<CheckGroup> findAll();
}
