package com.qqxhb.auth.jwt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import io.jsonwebtoken.Claims;

@Component
public class BaseController {

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected String company;
	protected Claims claims;

	@ModelAttribute
	public void setResAnReq(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		Object obj = request.getAttribute("user_claims");

		if (obj != null) {
			this.claims = (Claims) obj;
			this.company = (String) claims.get("company");
		}
	}

}
