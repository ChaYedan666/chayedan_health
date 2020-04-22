package com.itheima.health.mobile.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.chayedan.health.common.MessageConst;
import com.chayedan.health.common.RedisConst;
import com.chayedan.health.entity.Result;
import com.chayedan.health.pojo.Order;
import com.chayedan.health.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Map;

/**
 * @author chayedan666
 * @version 1.0
 * @className: OrderController
 * @description: 订单控制器
 * @date: 2020/4/21
 */
@RestController
@RequestMapping("/mobile/order")
public class OrderController {
    @Autowired
    private JedisPool jedisPool;

    @Reference
    private OrderService orderService;

    /**
     * 保存预约订单
     * @param map 表单数据
     * 1. 验证短信验证码
     * 2. 调用Service完成订单保存
     * 3. 预约成功短信通知预约人
     * @return
     */
    @RequestMapping("/submit")
    public Result submitOrder(@RequestBody Map<String,String> map){
        System.out.println("map:"+map.toString());
        // 验证短信验证码
        String telephone = map.get("telephone");
        String validateCode = map.get("validateCode");
        String codeInRedis = jedisPool.getResource().get(telephone+ RedisConst.SENDTYPE_ORDER);
        System.out.println("codeInRedis:"+codeInRedis+" telephone:"+telephone);
        if(codeInRedis == null || !codeInRedis.equals(validateCode)){
            return new Result(false, MessageConst.VALIDATECODE_ERROR);
        }
        Result result = null;
        try{
            // 调用Service完成订单保存
            map.put("orderType", Order.ORDERTYPE_WEIXIN);
            result = orderService.addOrder(map);
            if(result.isFlag()){
                // 发送短信通知
                String orderDate = map.get("orderDate");
                //SMSUtils.sendShortMessage(telephone,orderDate);
            }
        }catch(Exception e){
            e.printStackTrace();
            return new Result(false,MessageConst.ACTION_FAIL);
        }

        return result;
    }

    @RequestMapping("/findById")
    public Result findById(Integer id){
        Result result = null;
        try{
            result = orderService.findById4Detail(id);
            return result;
        }catch(Exception e){
            e.printStackTrace();
            return new Result(false,MessageConst.QUERY_ORDER_FAIL);
        }
    }
}
