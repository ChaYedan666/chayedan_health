package com.chayedan.health.dao;

import com.chayedan.health.pojo.Role;

import java.util.Set;

/**
 * @author chayedan666
 * @version 1.0
 * @className: RoleDao
 * @description:
 * @date: 2020/4/23
 */
public interface RoleDao {
    /**
     * 基于用户Id，获取角色对象
     * @param id
     * @return
     */
    Set<Role> findByUserId(Integer id);
}
