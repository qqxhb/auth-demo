package com.qqxhb.auth.shiro.service;

import org.springframework.stereotype.Service;

import com.qqxhb.auth.shiro.entity.User;

@Service
public class UserService {

	/**
	 * 模拟数据库查询用户
	 * 
	 * @param userName
	 * @return
	 */
	public User findByUserName(String userName) {
		if ("admin".equals(userName)) {
			return new User("1", "admin", "12345", "企业");
		}
		return null;
	}

	/**
	 * 模拟数据库查询用户
	 * 
	 * @param userId
	 * @return
	 */
	public User findById(String userId) {
		if ("1".equals(userId)) {
			return new User("1", "admin", "12345", "企业");
		}
		return null;
	}

}
