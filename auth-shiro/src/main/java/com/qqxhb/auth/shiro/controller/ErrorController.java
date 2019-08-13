package com.qqxhb.auth.shiro.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qqxhb.auth.shiro.vo.Result;
import com.qqxhb.auth.shiro.vo.ResultCode;

@RestController
@CrossOrigin
public class ErrorController {
	// 公共错误跳转
	@RequestMapping(value = "autherror")
	public Result autherror(int code) {
		return code == 1 ? new Result(ResultCode.UNAUTHENTICATED) : new Result(ResultCode.UNAUTHORISE);
	}
}