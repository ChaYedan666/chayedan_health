package com.chayedan.health.service;

import com.chayedan.health.entity.PageResult;
import com.chayedan.health.pojo.Setmeal;

import java.util.List;

/**
 * @author chayedan666
 * @version 1.0
 * @className: SetmealService
 * @description:
 * @date: 2020/4/16
 */
public interface SetmealService {
    /**
     * 添加体验套餐
     * @param setmeal 体检套餐基本信息
     * @param checkgroupIds 检查组选定列表
     */
    void add(Setmeal setmeal, Integer[] checkgroupIds);

    /**
     * 分页获取套餐数据
     * @param currentPage 当前页码
     * @param pageSize 默认条数
     * @param queryString 查询条件
     * @return
     */
    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    /**
     * 获取所有套餐的列表
     * @return
     */
    List<Setmeal> findAll();

    /**
     * 基于ID，获取套餐详情
     * @param id
     * @return
     */
    Setmeal findById(Integer id);
}
