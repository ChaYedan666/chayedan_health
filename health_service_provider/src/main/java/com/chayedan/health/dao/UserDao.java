package com.chayedan.health.dao;

import com.chayedan.health.pojo.User;

/**
 * @author chayedan666
 * @version 1.0
 * @className: UserDao
 * @description:
 * @date: 2020/4/23
 */
public interface UserDao {

    /**
     * 基于名字，获取用户信息
     * @param username
     * @return
     */
    User findByUsername(String username);
}
