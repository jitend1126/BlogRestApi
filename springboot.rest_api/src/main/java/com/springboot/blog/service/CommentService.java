package com.springboot.blog.service;

import java.util.List;

import com.springboot.blog.payload.commentDto;

public interface CommentService {
	commentDto createComments(long post_id,commentDto commentDto);
	List<commentDto> findPostById(long post_id);
	commentDto getCommentById(long post_id,long comment_id);
	commentDto updateCommentById(long post_id,long comment_id,commentDto commentDto);
	void  deleteCommentById(long post_id,long comment_id);

}
