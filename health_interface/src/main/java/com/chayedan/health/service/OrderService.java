package com.chayedan.health.service;

import com.chayedan.health.entity.Result;

import java.util.Map;

/**
 * @author chayedan666
 * @version 1.0
 * @className: OrderService
 * @description: 订单服务接口
 * @date: 2020/4/21
 */
public interface OrderService {
    /**
     * 保存预约订单
     * @param map 存储表单数据
     * @return
     */
    Result addOrder(Map<String,String> map);

    /**
     * 根据id查询预约信息，包括体检人信息、套餐信息
     * @param id
     * @return
     */
    Result findById4Detail(Integer id);
}
