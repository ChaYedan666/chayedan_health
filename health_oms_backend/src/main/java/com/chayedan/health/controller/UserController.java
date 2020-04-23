package com.chayedan.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.chayedan.health.common.MessageConst;
import com.chayedan.health.entity.Result;
import com.chayedan.health.pojo.User;
import com.chayedan.health.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
@RequestMapping("/user")
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

    @RequestMapping("/loginSuccess")
    public Result loginSuccess(){
        return new Result(true,MessageConst.LOGIN_SUCCESS);
    }
    @RequestMapping("/loginFail")
    public Result loginFail(){
        return new Result(false,"登录失败");
    }

    @RequestMapping("/getUsername")
    public Result getUsername() {
        try{
            //从授权框架上下文获取授权对象，再从授权对象获取授权框架用户对象User,最后获取用户名
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = (User)authentication.getPrincipal();
            if (user != null){
                return new Result(true,MessageConst.ACTION_SUCCESS,user.getUsername());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return new Result(false,MessageConst.ACTION_FAIL);
    }
}
