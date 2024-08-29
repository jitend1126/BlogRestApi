package com.springboot.blog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.payload.postDto;
import com.springboot.blog.payload.postResponse;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utils.AppConstants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class postController {
	
	private PostService postService;

	public postController(PostService postService) {
		super();
		this.postService = postService;
	}
	@Operation(
			summary = "Create Post Api",
			description = "Post api to save in db")
	@ApiResponse(
			responseCode = "201")
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<postDto> createpost(@Valid @RequestBody postDto postDto)
	{
		postDto response=postService.createPost(postDto);
		return  new ResponseEntity<>(response,HttpStatus.CREATED);
		

	}
	@Operation(
			summary = "getAllposts")
	@ApiResponse(
			responseCode = "200")
	@GetMapping
	public postResponse getAllposts(@RequestParam(value = "pageNo",required = false,defaultValue =AppConstants.DEFAULT_PAGE_NUMBER) int pageNo,
			@RequestParam(value = "PageSize",required = false,defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int PageSize,
			@RequestParam(value = "sortBy",required = false,defaultValue =AppConstants.DEFAULT_SORT_BY ) String sortBy,
			@RequestParam(value = "sortDir",required = false, defaultValue =AppConstants.DEFAULT_SORT_DIR) String sortDir)
	{
		postResponse responseDtos=postService.getAll(pageNo,PageSize,sortBy,sortDir);
		return responseDtos;
		
	}
	@Operation(
			summary = "get posts by Id")
	@ApiResponse(
			responseCode = "200")
	@GetMapping("/{id}")
	public ResponseEntity<postDto> getpostByid(@PathVariable long id)
	{
		postDto respnseDto=postService.getPostByid(id);
		return new ResponseEntity<>(respnseDto,HttpStatus.OK);
	
		
	}
	@Operation(
			summary = "updateByid")
	@ApiResponse(
			responseCode = "201")
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<postDto> updateByid(@PathVariable long id,@Valid @RequestBody postDto postDto)
	{
		postDto resDto=postService.updatepost(id, postDto);
		return new ResponseEntity<postDto>(resDto, HttpStatus.CREATED);
		
	}
	@Operation(
			summary = "deleteById")
	@ApiResponse(
			responseCode = "200")
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteById(@PathVariable long id)
	{
		postService.deletepost(id);
		return new ResponseEntity<String>("Post deleted successful",HttpStatus.ACCEPTED);
		
	}
	@Operation(
			summary = "getAllPostByCategory")
	@ApiResponse(
			responseCode = "200")
	@GetMapping("/category/{category_id}")
	public ResponseEntity<List<postDto>> getAllPostByCategory(@PathVariable long category_id)
	{
		List<postDto> postDto=postService.getPostByCategory(category_id);
		
		return new ResponseEntity<List<postDto>>(postDto, HttpStatus.OK);
		
	}
	
	
	

}
