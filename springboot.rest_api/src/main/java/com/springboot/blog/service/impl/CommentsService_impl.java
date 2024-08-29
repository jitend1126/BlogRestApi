package com.springboot.blog.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.blog.entity.comments;
import com.springboot.blog.entity.post;
import com.springboot.blog.exception.BlogApiException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.commentDto;
import com.springboot.blog.repository.commentRepository;
import com.springboot.blog.repository.postRepository;
import com.springboot.blog.service.CommentService;

@Service
public class CommentsService_impl implements CommentService {

	
	private commentRepository commentRepository;
	private postRepository postRepository;
	private ModelMapper mapper;
	@Autowired
	public CommentsService_impl(commentRepository commentRepository,
			postRepository postRepository,ModelMapper mapper) {
		super();
		this.commentRepository = commentRepository;
		this.postRepository = postRepository;
		this.mapper=mapper;
	}

	@Override
	
	public commentDto createComments(long post_id, commentDto commentDto) {
		comments comments=mapTocomments(commentDto);
		post post=postRepository.findById(post_id).orElseThrow(()-> new ResourceNotFoundException("post", "id", post_id));
		comments.setPost(post);
		
		comments comments2=commentRepository.save(comments);
		commentDto responseCommentDto=mapToDto(comments2);
		
		
		// TODO Auto-generated method stub
		return responseCommentDto;
	}
	
	private commentDto mapToDto(comments comments)
	{
		commentDto commentDto=mapper.map(comments, commentDto.class);
		
		
		return commentDto;
		
		}
	private comments mapTocomments(commentDto commentDto)
	{
		comments comments=mapper.map(commentDto, comments.class);
		
		return comments;
	}

	@Override
	public List<commentDto> findPostById(long post_id) {
		post post=postRepository.findById(post_id).orElseThrow(()-> new ResourceNotFoundException("post", "id", post_id));
		
		List<comments> comments=commentRepository.findByPostId(post_id);
		
		List<commentDto> response_list=comments.stream().map(comment->mapToDto(comment)).collect(Collectors.toList());
		
		return response_list;
		// TODO Auto-generated method stub

	}

	@Override
	public commentDto getCommentById(long post_id, long comment_id) {
		post post=postRepository.findById(post_id).orElseThrow(()-> new ResourceNotFoundException("post", "id", post_id));
		
		comments comments=commentRepository.findById(comment_id).orElseThrow(()-> new ResourceNotFoundException("Comment", "commentId", comment_id));
		// TODO Auto-generated method stub
				if(comments.getPost().getId()!=(post.getId()))
						{
					throw new BlogApiException("Comment  is not found  Post ID", HttpStatus.BAD_REQUEST);
						}
				else {
					
						commentDto commentDto=mapToDto(comments);
						return commentDto;
						
					
				}

	}

	@Override
	public commentDto updateCommentById(long post_id, long comment_id,commentDto commentDto) {
		post post=postRepository.findById(post_id).orElseThrow(()->new ResourceNotFoundException("post", "id", post_id));
		
		comments comments=commentRepository.findById(comment_id).orElseThrow(()->new ResourceNotFoundException("comments", "id", comment_id));
		// TODO Auto-generated method stub
		if(comments.getPost().getId()!=post.getId())
		{
			throw new BlogApiException("comment is not Found with Post ID", HttpStatus.BAD_REQUEST);
		}
		
				
				comments.setBody(commentDto.getBody());
				comments.setEmail(commentDto.getEmail());
				comments.setName(commentDto.getName());
				
				comments resComments=commentRepository.save(comments);
				commentDto commentDto2=mapToDto(resComments);
				return commentDto2;
			
		
	
	}

	@Override
	public void deleteCommentById(long post_id, long comment_id) {
		post post=postRepository.findById(post_id).orElseThrow(()->new ResourceNotFoundException("post","id", post_id));
		
		comments comments=commentRepository.findById(comment_id).orElseThrow(()-> new ResourceNotFoundException("comments", "id", comment_id));
		if(comments.getPost().getId()!=post.getId())
		{
			throw new BlogApiException("comment is not Found with Post ID", HttpStatus.BAD_REQUEST);
		}
		commentRepository.deleteById(comment_id);
		
		// TODO Auto-generated method stub
	
	}

}
