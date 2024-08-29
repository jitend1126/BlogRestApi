package com.springboot.blog.payload;

import java.util.List;
import java.util.Set;

import com.springboot.blog.entity.category;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data


public class postDto {
	private long id;
	@NotEmpty
	@Size(min = 2,message = "Title Should have atleast 2chararcter")
	private String title;
	@NotEmpty
	@Size(min = 2,message = "content Should have atleast 2chararcter")
	private String content;
	@NotEmpty
	@Size(min = 2,message = "description Should have atleast 2chararcter")
	private String description;
	private Set<commentDto> commentDtos;
	private  long categoryId;
	
	
	
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<commentDto> getComments() {
		return commentDtos;
	}
	public void setComments(Set<commentDto> commentDtos) {
		this.commentDtos = commentDtos;
	}
	
}
