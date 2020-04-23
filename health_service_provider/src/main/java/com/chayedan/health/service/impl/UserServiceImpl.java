package com.chayedan.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.chayedan.health.dao.PermissionDao;
import com.chayedan.health.dao.RoleDao;
import com.chayedan.health.dao.UserDao;
import com.chayedan.health.pojo.Permission;
import com.chayedan.health.pojo.Role;
import com.chayedan.health.pojo.User;
import com.chayedan.health.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

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

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PermissionDao permissionDao;



    @Override
    public boolean login(String username, String password) {
        log.debug("u:{},p:{}",username,password);
        if ("admin".equals(username) && "123".equals(password)){
            return true;
        }
        return false;
    }

    @Override
    public User findByUsername(String username) {
        // 从数据库读取用户信息
        //log.debug("service_provide...从数据库读取用户信息..findByUsername:{},p:{}",username);
        User user = userDao.findByUsername(username);
        if (user == null){
            return null;
        }
        // 获取角色列表
        Set<Role> roles = roleDao.findByUserId(user.getId());
        for (Role role:roles){
            Integer roleId = role.getId();
            // 获取权限列表
            Set<Permission> permissions = permissionDao.findByRoleId(roleId);
            if (permissions != null && permissions.size() > 0){
                role.setPermissions(permissions);
            }
        }
        if(roles !=null && roles.size()>0){
            user.setRoles(roles);
        }
        return user;
    }
}
