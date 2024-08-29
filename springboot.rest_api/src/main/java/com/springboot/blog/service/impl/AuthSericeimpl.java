package com.springboot.blog.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.apache.catalina.mapper.Mapper;
import org.hibernate.bytecode.internal.bytebuddy.PrivateAccessorException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.blog.entity.Role;
import com.springboot.blog.entity.User;
import com.springboot.blog.exception.BlogApiException;
import com.springboot.blog.payload.RegisterDto;
import com.springboot.blog.payload.loginDto;
import com.springboot.blog.repository.roleRepository;
import com.springboot.blog.repository.userRepository;
import com.springboot.blog.service.AuthService;

@Service
public class AuthSericeimpl implements AuthService {
	
	private AuthenticationManager authenticationManager;
	private ModelMapper mapper;
	private userRepository userRepository;
	private roleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	
	public AuthSericeimpl(AuthenticationManager authenticationManager, ModelMapper mapper,
			com.springboot.blog.repository.userRepository userRepository,
			com.springboot.blog.repository.roleRepository roleRepository, PasswordEncoder passwordEncoder) {
		super();
		this.authenticationManager = authenticationManager;
		this.mapper = mapper;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}


	@Override
	public String login(loginDto loginDto) {
		Authentication authenticationManager1=authenticationManager.authenticate
		(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authenticationManager1);
		
		return "User login Successful!";
	}





	@Override
	public String registration(RegisterDto registerDto) {
		if(userRepository.existsByUsername(registerDto.getUsername()))
		{
			throw new BlogApiException("User Already Exist!", HttpStatus.BAD_REQUEST);
		}
		if(userRepository.existsByEmail(registerDto.getEmail()))
		{
			throw new BlogApiException("Email Already Exist!", HttpStatus.BAD_REQUEST);
		}
		User user=new User();
		user.setName(registerDto.getName());
		user.setEmail(registerDto.getEmail());
		user.setUsername(registerDto.getUsername());
		user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
		Set<Role> roles=new HashSet<>();
		Role userRole=roleRepository.findByName("ROLE_USER").get();
		roles.add(userRole);
		user.setRole(roles);
		userRepository.save(user);
		
		
		
		return "Registration  Successful";
		
//		User user=mapToUser(registerDto);
//		User user2=userRepository.save(user);
//		RegisterDto registerDto2=mapToDto(user2);
//		return registerDto2;
//		
		// TODO Auto-generated method stub
	}
//private User mapToUser(RegisterDto registerDto)
//{
//	User user=mapper.map(registerDto,User.class);
//	return user;
//	
//}
// private RegisterDto mapToDto(User user)
// {
//	 RegisterDto registerDto=mapper.map(user, RegisterDto.class);
//	return registerDto;
//	 
// }
}
