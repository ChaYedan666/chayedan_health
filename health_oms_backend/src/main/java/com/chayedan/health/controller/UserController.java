package com.chayedan.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.chayedan.health.common.MessageConst;
import com.chayedan.health.entity.Result;
import com.chayedan.health.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chayedan666
 * @version 1.0
 * @className: UserController
 * @description:
 * @date: 2020/4/14
 */
@RestController
@RequestMapping("/web/user")
public class UserController {
    @Reference
    private UserService userService;

    @RequestMapping("/login")
    public Result login(String username, String password){
        try {
            if (userService.login(username,password)){
                return new Result(true, MessageConst.ACTION_SUCCESS,username);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(false, MessageConst.ACTION_FAIL);


    }
}
