package com.qqxhb.auth.shiro.service;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;

import com.qqxhb.auth.shiro.common.AuthShiroRealm;
import com.qqxhb.auth.shiro.entity.User;
import com.qqxhb.auth.shiro.vo.ProfileResult;

public class UserRealm extends AuthShiroRealm {
	@Override
	public void setName(String name) {
		super.setName("customRealm");
	}

	@Autowired
	private UserService userService;

	// 认证方法
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		// 1.获取用户的手机号和密码
		UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
		String username = upToken.getUsername();
		String password = new String(upToken.getPassword());
		// 2.根据手机号查询用户
		User user = userService.findByUserName(username);
		// 3.判断用户是否存在，用户密码是否和输入密码一致
		if (user != null && user.getPassword().equals(password)) {
			// 4.构造安全数据并返回（安全数据：用户基本数据，权限信息 profileResult）
			ProfileResult result = new ProfileResult(user);
			// 构造方法：安全数据，密码，realm域名
			SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(result, user.getPassword(), this.getName());
			return info;
		}
		// 返回null，会抛出异常，标识用户名和密码不匹配
		return null;
	}
}