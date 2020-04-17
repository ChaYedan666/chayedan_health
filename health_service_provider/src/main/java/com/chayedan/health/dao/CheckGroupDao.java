package com.chayedan.health.dao;

import com.chayedan.health.pojo.CheckGroup;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author chayedan666
 * @version 1.0
 * @className: CheckGroupDao
 * @description: 检查组Dao接口
 * @date: 2020/4/15
 */
public interface CheckGroupDao {

    /**
     * 新增检查组
     * @param checkGroup
     */
    void add(CheckGroup checkGroup);

    /**
     * 新增检查组与检查项之间的关系
     * @param oneMap
     */
    void addCheckGroupAndCheckItem(Map oneMap);

    /**
     * 基于条件分页获取检查组列表
     * @param queryString
     * @return
     */
    Page<CheckGroup> selectByCondition(@Param("queryString") String queryString);

    /**
     * 根据ID获取检查组
     * @param id
     * @return
     */
    CheckGroup findById(@Param("id") Integer id);

    /**
     * 获取某一检查组的检查项ID列表
     * @param id
     * @return
     */
    List<Integer> findCheckItemIdsByCheckGroupId(@Param("id") Integer id);

    /**
     * 基于ID，更新检查组基本信息
     * @param checkGroup
     */
    void edit(CheckGroup checkGroup);

    /**
     * 基于检查组ID，删除与之关联的检查项
     * @param id
     */
    void deleteCheckItemsListByCheckGroupId(@Param("id") Integer id);

    /**
     * 获取所有检查组数据
     * @return
     */
    List<CheckGroup> findAll();
}
