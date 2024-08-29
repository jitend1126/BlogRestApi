package com.springboot.blog.repository;

import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blog.entity.category;

public interface categoryRepository extends JpaRepository<category, Long>{

}
