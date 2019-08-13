package com.qqxhb.auth.shiro.exception;

import com.qqxhb.auth.shiro.vo.ResultCode;

import lombok.Getter;

@Getter
public class CommonException extends Exception  {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ResultCode resultCode;

    public CommonException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }
}