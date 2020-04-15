package com.chayedan.health.dao;

import com.chayedan.health.pojo.CheckItem;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chayedan666
 * @version 1.0
 * @className: CheckItemDao
 * @description: 检查项Dao接口
 * @date: 2020/4/15
 */
public interface CheckItemDao {
    /**
     * 新增检查项
     * @param checkItem 检查项数据
     */
    void add(CheckItem checkItem);

    /**
     * 分页查询
     * @param queryString
     * @return
     */
    Page<CheckItem> selectByCondition(@Param("queryString") String queryString);
    /**
     * 基于检查项ID，查询是否有关联数据
     * @param checkItemId 检查项ID
     * @return
     */
    Long countCheckItemsById(@Param("checkItemId") Integer checkItemId);

    /**
     * 基于ID删除
     * @param id 检查项ID
     */
    void deleteCheckItemById(@Param("id") Integer id);

    /**
     * 根据ID查找相关项目
     * @param id
     * @return
     */
    CheckItem findById(@Param("id") Integer id);

    /**
     * 编辑数据
     * @param checkItem
     */
    void edit(CheckItem checkItem);
}
