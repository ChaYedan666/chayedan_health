package com.itheima.health.mobile.controller;

import com.chayedan.health.common.MessageConst;
import com.chayedan.health.common.RedisConst;
import com.chayedan.health.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

/**
 * @author chayedan666
 * @version 1.0
 * @className: ValidateCodeController
 * @description: 验证码控制器
 * @date: 2020/4/20
 */
@RestController
@RequestMapping("/mobile/validateCode")
public class ValidateCodeController {

    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/send4Order")
    public Result send4Order(String telephone){
        try {
            // 生成验证码 我这里直接固定为1234
            String validateCode = "1234";
            // 存入redis
            jedisPool.getResource().setex(telephone+ RedisConst.SENDTYPE_ORDER,5*60,validateCode);

            return new Result(true, MessageConst.SEND_VALIDATECODE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConst.SEND_VALIDATECODE_FAIL);
        }
    }

    //手机快速登录时发送手机验证码
    @RequestMapping("/send4Login")
    public Result send4Login(String telephone){
        //Integer code = ValidateCodeUtils.generateValidateCode(6);//生成6位数字验证码
        Integer code = 1234;
        try {
            //发送短信
            //SMSUtils.sendShortMessage(telephone,code.toString());
            //return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
            System.out.println("发送的手机验证码为：" + code);
            //将生成的验证码缓存到redis
            jedisPool.getResource().setex(telephone+RedisConst.SENDTYPE_LOGIN,
                    5 * 60,
                    code.toString());
            //验证码发送成功
            return new Result(true,MessageConst.SEND_VALIDATECODE_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("验证码发送失败");
            return new Result(false,MessageConst.SEND_VALIDATECODE_FAIL);
        }

    }
}
