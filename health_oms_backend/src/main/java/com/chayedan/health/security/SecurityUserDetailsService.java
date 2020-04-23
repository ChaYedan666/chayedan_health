package com.chayedan.health.security;

import com.alibaba.dubbo.config.annotation.Reference;
import com.chayedan.health.pojo.Permission;
import com.chayedan.health.pojo.Role;
import com.chayedan.health.pojo.User;
import com.chayedan.health.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

/**
 * @author ：seanyang
 * @date ：Created in 2019/7/22
 * @description ：
 * @version: 1.0
 */
public class SecurityUserDetailsService implements UserDetailsService {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	// 模拟数据库的用户记录，如下User类是health_common中的自定义实体类User
	// 修改Role、Permission，为其增加不带参、带参构造方法
	private static Map<String, User> userDb = new HashMap();
	@Reference
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 模拟从数据库读取用户信息
		User currUser = userService.findByUsername(username);
		//User currUser = userDb.get(username);
		if (currUser == null){
			return  null;
		}
		// 获取用户角色及权限
		List<GrantedAuthority> authList = new ArrayList<>();
		for (Role role:currUser.getRoles()){
			// 角色关键词
			authList.add(new SimpleGrantedAuthority(role.getKeyword()));
			Set<Permission> permissions = role.getPermissions();
			// 权限关键词
			for(Permission p:permissions){
				authList.add(new SimpleGrantedAuthority(p.getKeyword()));
			}
		}
		// 如果密码未加密，必须用默认加密规则加密
		// 如果密码已加密，则不用使用默认规则加密
		String passwordInDb = currUser.getPassword();
		//String authPassword = "{noop}"+passwordInDb;
		//String authPassword = passwordEncoder.encode(currUser.getPassword());
		// 构建UserDetails对象
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(username,passwordInDb,authList);
		return userDetails;
	}
}
