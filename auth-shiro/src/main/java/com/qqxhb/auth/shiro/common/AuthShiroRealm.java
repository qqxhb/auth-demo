package com.qqxhb.auth.shiro.common;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.qqxhb.auth.shiro.vo.ProfileResult;

public class AuthShiroRealm extends AuthorizingRealm {
	@Override
	public void setName(String name) {
		super.setName("authShiroRealm");
	}

	// 授权方法
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		// 1.获取安全数据
		ProfileResult result = (ProfileResult) principalCollection.getPrimaryPrincipal();
		// 2.获取权限信息
		Set<String> apisPerms = (Set<String>) result.getRoles().get("apis");
		// 3.构造权限数据，返回值
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(apisPerms);
		return info;
	}

	/**
	 * 认证方法
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		return null;
	}
}