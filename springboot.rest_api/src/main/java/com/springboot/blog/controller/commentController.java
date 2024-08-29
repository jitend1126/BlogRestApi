package com.springboot.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.springboot.blog.payload.commentDto;
import com.springboot.blog.service.CommentService;
import com.springboot.blog.service.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/posts/")
public class commentController {
	
	private CommentService commentService;
	private PostService postService;
	

	@Autowired
	public commentController(CommentService commentService, PostService postService) {
		super();
		this.commentService = commentService;
		this.postService = postService;
	}


	@PostMapping("{id}/comments")
	public ResponseEntity<commentDto> createComments(@PathVariable long id,@Valid @RequestBody commentDto commentDto )
	{
		commentDto respnse=commentService.createComments(id, commentDto);
		return new ResponseEntity<>(respnse,HttpStatus.CREATED);
		
		
	}
	@GetMapping("{id}/comments")
	public ResponseEntity<List<commentDto>> allCommentsByid(@PathVariable long id)
	{
		List<commentDto> resCommentDtos=commentService.findPostById(id);
		
		return new ResponseEntity<>(resCommentDtos, HttpStatus.OK);
		
	}
	@GetMapping("{postid}/comments/{id}")
	public ResponseEntity<commentDto> getCommentsById(@PathVariable(value="postid") 
	long post_id,@PathVariable(value = "id") long comment_id) {
		commentDto resCommentDto=commentService.getCommentById(post_id, comment_id);
		return new ResponseEntity<commentDto>(resCommentDto, HttpStatus.OK);
		
	}
	@PutMapping("{postid}/comments/{id}")
	public ResponseEntity<commentDto> updateCommentsById(@PathVariable(value="postid")
	long post_id,@PathVariable(value = "id") long id,@Valid @RequestBody commentDto commentDto)
	{
		commentDto commentDto2=commentService.updateCommentById(post_id, id, commentDto);
		return new ResponseEntity<commentDto>(commentDto2, HttpStatus.CREATED);
	
		
	}
	@DeleteMapping("{postid}/comments/{id1}")
	public ResponseEntity<String> deleteCommentsById(@PathVariable(value = "postid") long post_id,@PathVariable(value = "id1") long id)
	{
		commentService.deleteCommentById(post_id, id);
		return new ResponseEntity<>("Comment is deleted Successfully",HttpStatus.OK);
		
	}
}
