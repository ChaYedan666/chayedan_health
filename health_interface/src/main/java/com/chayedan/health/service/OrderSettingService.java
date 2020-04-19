package com.chayedan.health.service;

import com.chayedan.health.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

/**
 * @author chayedan666
 * @version 1.0
 * @className: OrderSettingService
 * @description: 预约管理接口
 * @date: 2020/4/17
 */
public interface OrderSettingService {
    /**
     *  添加预约内容
     * @param list 预约列表
     */
    void add(List<OrderSetting> list);

    /**
     * 获取某个月份的设置列表
     * @param date 月份
     * @return 列表包含Map,预约及
     */
    List<Map> getOrderSettingByMonth(String date);

    void editNumberByDate(OrderSetting orderSetting);
}
