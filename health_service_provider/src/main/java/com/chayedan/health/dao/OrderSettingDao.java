package com.chayedan.health.dao;

import com.chayedan.health.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author chayedan666
 * @version 1.0
 * @className: OrderSettingDao
 * @description: 预约Dao接口
 * @date: 2020/4/18
 */
public interface OrderSettingDao {
    /**
     * 添加预约设置
     * @param orderSetting 预约设置数据
     */
    void add(OrderSetting orderSetting);

    /**
     * 基于预约日期更新预约设置
     * @param orderSetting 预约设置数据
     */
    void updateOrderSettingByOrderDate(OrderSetting orderSetting);

    /**
     * 统计某一日期下的数据
     * @param orderDate
     * @return
     */
    Long countByOrderDate(Date orderDate);

    /**
     * 获取某月数据
     * @param date 提供月初与月末时间段
     */
    List<OrderSetting> getOrderSettingByMonth(Map date);

}
