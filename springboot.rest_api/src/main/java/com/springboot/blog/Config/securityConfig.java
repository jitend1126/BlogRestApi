package com.springboot.blog.Config;

import javax.security.sasl.AuthorizeCallback;

import org.hibernate.annotations.Bag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;

import com.springboot.blog.Security.CustomUserDetailsService;

@Configuration
@EnableMethodSecurity
public class securityConfig {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	public securityConfig(UserDetailsService userDetailsService) {
	
		this.userDetailsService = userDetailsService;
	}



	@Bean
	public static PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	 	@Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception
	    {
			return configuration.getAuthenticationManager();
	    	
	    }
	
	   
	@Bean
	 SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
	 {
		 httpSecurity.csrf().
		 disable().authorizeHttpRequests((authorize)->authorize.requestMatchers(HttpMethod.GET,"/api/**")
				 .permitAll()
				 .requestMatchers("/v3/api-docs").permitAll()
				 .anyRequest()
				 .authenticated())
		 .httpBasic(Customizer.withDefaults());
		return httpSecurity.build();
		 
	 }
//	@Bean
//	public UserDetailsService userDetailsService()
//	{
//		UserDetails jitendra=User.builder().username("jitendra").password(passwordEncoder().encode("jitendra")).roles("USER").build();
//		UserDetails admin=User.builder().username("admin").password(passwordEncoder().encode("admin")).roles("ADMIN").build();
//		
//		return new InMemoryUserDetailsManager(jitendra,admin);
//		
//	}

}
