package com.springboot.blog.service;

import java.util.List;

import com.springboot.blog.payload.postDto;
import com.springboot.blog.payload.postResponse;

public interface PostService {
	
	public postDto createPost(postDto postDto);
	public postResponse getAll(int pageNo, int pageSizeint,String SortBy,String sortDir);
	public postDto getPostByid(long id) ;
	public postDto updatepost(long id, postDto postDto);
	public void deletepost(long id);
	public List<postDto> getPostByCategory(long category_id);
		
	
	}
