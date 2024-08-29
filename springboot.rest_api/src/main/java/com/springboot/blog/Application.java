package com.springboot.blog;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.springboot.blog.entity.Role;
import com.springboot.blog.repository.roleRepository;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "SpringBootApplication Api",
				description = "Spring boot Blog Api documentation")
				
				
		)
public class Application implements CommandLineRunner {
	@Bean
	public ModelMapper mapper()
	{
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.print("Jitendra");
	}
	@Autowired
		private roleRepository roleRepository;
	@Override
	public void run(String... args) throws Exception {
		Role adminRole=new Role();
		adminRole.setName("ROLE_ADMIN");
		roleRepository.save(adminRole);
		Role userRole=new Role();
		userRole.setName("ROLE_USER");
		roleRepository.save(userRole);
		
		// TODO Auto-generated method stub
		
	}

}
