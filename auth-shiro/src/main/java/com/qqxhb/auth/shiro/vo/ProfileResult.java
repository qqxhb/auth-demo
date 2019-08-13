package com.qqxhb.auth.shiro.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.crazycake.shiro.AuthCachePrincipal;

import com.qqxhb.auth.shiro.entity.User;

import lombok.Data;

@Data
public class ProfileResult implements Serializable, AuthCachePrincipal {
	private String userName;
	private String company;
	// 角色权限信息
	private Map<String, Object> roles = new HashMap<>();

	public ProfileResult(User user) {
		this.userName = user.getUserName();
		this.company = user.getCompany();

		Set<String> menus = new HashSet<>();
		Set<String> points = new HashSet<>();
		Set<String> apis = new HashSet<>();
		// 根据用户去获取角色权限信息：菜单，按钮，接口
		this.roles.put("menus", menus);
		this.roles.put("points", points);
		this.roles.put("apis", apis);
	}

	@Override
	public String getAuthCacheKey() {
		return null;
	}
}
