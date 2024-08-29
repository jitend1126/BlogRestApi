package com.springboot.blog.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.hibernate.query.Page;
import org.hibernate.transform.ToListResultTransformer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import com.springboot.blog.entity.category;
import com.springboot.blog.entity.post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.postDto;
import com.springboot.blog.payload.postResponse;
import com.springboot.blog.repository.categoryRepository;
import com.springboot.blog.repository.postRepository;
import com.springboot.blog.service.PostService;

import jakarta.persistence.Entity;
@Service

public class postserviceimpl implements PostService {
	
	private postRepository postRepository;
	private ModelMapper mapper;
	private categoryRepository categoryRepository;
	@Autowired
	public postserviceimpl(postRepository postRepository,ModelMapper mapper,categoryRepository categoryRepository) {
		super();
		this.postRepository = postRepository;
		this.mapper=mapper;
		this.categoryRepository=categoryRepository;
	}

	public postDto createPost(postDto postDto)
	//Dto to entity
	{
		category category=categoryRepository.findById(postDto.getCategoryId()).orElseThrow(()->new ResourceNotFoundException("category", "id", postDto.getCategoryId()));
		post post=maptoPost(postDto);
		post.setCategory(category);
		post newpost=postRepository.save(post);
		postDto responseDto=maptoDto(newpost);
		return responseDto;
		
	
	
	}
	@Override
	public postResponse getAll(int pageNo,int PageSize,String sortBy,String sortDir)
	{
		Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		
		Pageable pageable=(Pageable) PageRequest.of(pageNo, PageSize, sort);
		
		
		org.springframework.data.domain.Page<post> posts=postRepository.findAll(pageable);
		
		List<post> listPosts=posts.getContent();
		
		
		List<postDto> resDtos=listPosts.stream().map(post->maptoDto(post)).collect(Collectors.toList());
		postResponse response=new postResponse();
		response.setContentDtos(resDtos);
		response.setPageNo(posts.getNumber());
		response.setPageSize(posts.getSize());
		response.setTotalElement(posts.getTotalElements());
		response.setTotalPage(posts.getTotalPages());
		response.setLastPage(posts.isLast());
		
		
		
		return response;
		
	}
	
	private post maptoPost (postDto postDto) {
		post post=mapper.map(postDto,post.class);
		
		return post;
		
	}
	private postDto maptoDto(post post)
	{
		postDto postDto1=mapper.map(post, postDto.class);
		
		
		return postDto1;
		
	}

	@Override
	public postDto getPostByid(long id) {
		post post=postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
			postDto responseDto=maptoDto(post);
			return responseDto;
			
		
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public postDto updatepost(long id, postDto postDto) {
		post post=maptoPost(postDto);
		post post2=postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		category category=categoryRepository.findById(postDto.getCategoryId()).orElseThrow(()-> new ResourceNotFoundException("Category", "id", postDto.getCategoryId()));
		post2.setContent(post.getContent());
		post2.setDescription(post.getDescription());
		post2.setTitle(post.getTitle());
		post2.setCategory(category);
		post post3=postRepository.save(post2);
		postDto responseDto=maptoDto(post3);
		return responseDto;
		
	
		
		// TODO Auto-generated method stub
	}

	@Override
	public void deletepost(long id) {
		postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		
		postRepository.deleteById(id);
		
		
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<postDto> getPostByCategory(long category_id) {
		category category=categoryRepository.findById(category_id).orElseThrow(()-> new ResourceNotFoundException("category", "id", category_id));
		List<post> posts=postRepository.findByCategoryId(category_id);
		List<postDto> postDtos=posts.stream().map((post)->maptoDto(post)).collect(Collectors.toList());
		return postDtos;
		
		// TODO Auto-generated method stub
	}
}
