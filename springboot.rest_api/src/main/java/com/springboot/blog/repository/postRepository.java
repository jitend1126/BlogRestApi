package com.springboot.blog.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.blog.entity.post;

@Repository
public interface postRepository extends JpaRepository<post,Long> {
	
	List<post> findByCategoryId(long category_id);

}
