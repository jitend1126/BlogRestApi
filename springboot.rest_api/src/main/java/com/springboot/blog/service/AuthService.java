package com.springboot.blog.service;

import com.springboot.blog.payload.RegisterDto;
import com.springboot.blog.payload.loginDto;


public interface AuthService {
	public String login(loginDto loginDto); 
	public String registration(RegisterDto registerDto);

}
