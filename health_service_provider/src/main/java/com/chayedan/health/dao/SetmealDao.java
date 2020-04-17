package com.chayedan.health.dao;

import com.chayedan.health.pojo.Setmeal;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author chayedan666
 * @version 1.0
 * @className: SetmealDao
 * @description: 检查套餐Dao接口
 * @date: 2020/4/16
 */

public interface SetmealDao {
    /**
     * 添加套餐信息
     * @param setmeal 套餐对象
     */
    void add(Setmeal setmeal);

    /**
     * 添加套餐与检查组关联关系
     * @param map 套餐ID与检查组ID对于关系
     */
    void addSetmealAndCheckGroup(Map<String,Integer> map);

    /**
     * 基于条件查询数据
     * @param queryString 条件
     * @return
     */
    Page<Setmeal> selectByCondition(@Param("queryString") String queryString);
}
