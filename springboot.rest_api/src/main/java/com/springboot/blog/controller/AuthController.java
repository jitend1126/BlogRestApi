package com.springboot.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.event.PublicInvocationEvent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.payload.RegisterDto;
import com.springboot.blog.payload.loginDto;
import com.springboot.blog.service.AuthService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.Registration;

@Tag(
		name = "CRUD API FOR AUTHENTICATION")
@RestController
@RequestMapping("api/auth/")
public class AuthController {
	
	private AuthService authService;
	@Autowired
	public AuthController(AuthService authService) {
		super();
		this.authService = authService;
	}
	
	
	@PostMapping("login")
	public ResponseEntity<String> userlogin(@RequestBody loginDto loginDto)
	{
		String res=authService.login(loginDto);
		
		return new ResponseEntity<String>(res, HttpStatus.OK);
		
	}
	//Rest API for  
	@PostMapping(value = {"Registration","signup"})
	public ResponseEntity<String> Registration(@RequestBody RegisterDto registerDto)
	{
		String res=authService.registration(registerDto);
		
		return  new ResponseEntity<String>(res, HttpStatus.CREATED);
		
	}
	
	

}
