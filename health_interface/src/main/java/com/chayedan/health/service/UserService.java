package com.chayedan.health.service;

public interface UserService {
    /**
     * 用户登录
     * @param username 用户名
     * @param password 密 码
     * @return
     */
    boolean login(String username,String password);
}
