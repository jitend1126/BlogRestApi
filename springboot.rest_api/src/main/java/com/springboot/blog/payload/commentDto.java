package com.springboot.blog.payload;

import com.springboot.blog.entity.post;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class commentDto {
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	private Long id;
	@NotNull
	
	private String name;
	@NotEmpty
	private String email;
	@NotEmpty
	@Size(min = 3, max = 100,message = "comment should between 3 and 100 character")
	private String body;
	

}
