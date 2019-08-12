package com.qqxhb.auth.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.qqxhb.auth.jwt.utils.JwtUtil;

@SpringBootApplication
public class JWTAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(JWTAuthApplication.class, args);
	}

	@Bean
	public JwtUtil jwtUtil() {
		return new JwtUtil();
	}
}
