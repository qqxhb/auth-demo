package com.qqxhb.auth.jwt.vo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.qqxhb.auth.jwt.entity.User;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProfileResult {
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
		//根据用户去获取角色权限信息：菜单，按钮，接口
		this.roles.put("menus", menus);
		this.roles.put("points", points);
		this.roles.put("apis", apis);
	}
}
