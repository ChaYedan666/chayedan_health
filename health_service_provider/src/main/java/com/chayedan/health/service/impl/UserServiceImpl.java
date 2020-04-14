package com.chayedan.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.chayedan.health.service.UserService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author chayedan666
 * @version 1.0
 * @className: UserServiceImpl
 * @description:
 * @date: 2020/4/14
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Override
    public boolean login(String username, String password) {
        log.debug("u:{},p:{}",username,password);
        if ("admin".equals(username) && "123".equals(password)){
            return true;
        }
        return false;
    }
}
