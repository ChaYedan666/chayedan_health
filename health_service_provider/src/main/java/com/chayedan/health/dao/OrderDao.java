package com.chayedan.health.dao;

import com.chayedan.health.pojo.Order;

import java.util.List;
import java.util.Map;

/**
 * @author chayedan666
 * @version 1.0
 * @className: OrderDao
 * @description: 订单Dao接口
 * @date: 2020/4/21
 */
public interface OrderDao {
    /**
     * 保存订单
     * @param order 订单数据
     */
    void add(Order order);

    /**
     *  基于条件查找订单数据
     * @param order
     * @return
     */
    List<Order> findByCondition(Order order);

    /**
     * 基于ID获取预约详情
     * @param id
     * @return
     */
    Map<String,Object> findById4Detail(Integer id);
}
