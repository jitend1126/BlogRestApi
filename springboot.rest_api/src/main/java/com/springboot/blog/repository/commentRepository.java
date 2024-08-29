package com.springboot.blog.repository;

import java.util.List;
import java.util.function.LongFunction;

import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.blog.entity.comments;
@Repository
public interface commentRepository extends JpaRepository<comments,Long>{
	List<comments> findByPostId(long id);
	

}
