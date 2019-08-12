package com.qqxhb.auth.jwt;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthApplicationTest {
	public static void main(String[] args) {
		String token = createJwt();
//		System.out.println(token);
		parseJwt(token);
	}

	/**
	 * 创建Token
	 * 
	 * @return
	 */
	public static String createJwt() {
		long exp = System.currentTimeMillis() + 60 * 1000;// 过期时间一分钟
		JwtBuilder builder = Jwts.builder().setId("666").setSubject("君莫笑").setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "qqxhb")
				.setExpiration(new Date(exp))
				.claim("role", "admin")
				.claim("logo", "logo.png");
		return builder.compact();
	}

	/**
	 * 解析token
	 */
	public static void parseJwt(String token) {
		Claims claims = Jwts.parser().setSigningKey("qqxhb").parseClaimsJws(token).getBody();
		System.out.println("id:" + claims.getId());
		System.out.println("subject:" + claims.getSubject());
		System.out.println("role:" + claims.get("role"));
		System.out.println("logo:" + claims.get("logo"));
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		System.out.println("签发时间:"+sdf.format(claims.getIssuedAt()));
        System.out.println("过期时间:"+sdf.format(claims.getExpiration()));
        System.out.println("当前时间:"+sdf.format(new Date()) );
	}

}
