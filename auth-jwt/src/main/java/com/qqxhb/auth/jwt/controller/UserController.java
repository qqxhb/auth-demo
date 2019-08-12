package com.qqxhb.auth.jwt.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qqxhb.auth.jwt.entity.User;
import com.qqxhb.auth.jwt.service.UserService;
import com.qqxhb.auth.jwt.utils.JwtUtil;
import com.qqxhb.auth.jwt.vo.ProfileResult;
import com.qqxhb.auth.jwt.vo.Result;
import com.qqxhb.auth.jwt.vo.ResultCode;

@RequestMapping
@RestController
public class UserController extends BaseController {

	@Autowired
	UserService userService;
	@Autowired
	JwtUtil jwtUtil;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Result login(@RequestBody Map<String, String> loginMap) {
		String userName = loginMap.get("userName");
		String password = loginMap.get("password");
		User user = userService.findByUserName(userName);
		// 登录失败
		if (user == null || !user.getPassword().equals(password)) {
			return new Result(ResultCode.MOBILEORPASSWORDERROR);
		} else {// 登录成功
			Map<String, Object> map = new HashMap<>();
			map.put("company", user.getCompany());
			String token = jwtUtil.createJWT(user.getId(), user.getUserName(), map);
			return new Result(ResultCode.SUCCESS, token);
		}
	}

	/**
	 * 用户登录成功之后，获取用户信息 
	 */
	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public Result profile(HttpServletRequest request) throws Exception {

		String userid = claims.getId();
		// 获取用户信息
		User user = userService.findById(userid);
		// 根据不同的用户级别获取用户权限
		ProfileResult result = new ProfileResult(user);
		return new Result(ResultCode.SUCCESS, result);
	}
}
