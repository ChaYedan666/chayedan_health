package com.chayedan.health.dao;

import com.chayedan.health.pojo.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * @author chayedan666
 * @version 1.0
 * @className: PermissionDao
 * @description:
 * @date: 2020/4/23
 */
public interface PermissionDao {
    Set<Permission> findByRoleId(@Param("id") Integer id);
}
