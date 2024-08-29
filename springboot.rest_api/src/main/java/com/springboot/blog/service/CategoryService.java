package com.springboot.blog.service;

import com.springboot.blog.payload.CategoryDto;
import java.util.List;



public interface CategoryService {
	public CategoryDto createCategory(CategoryDto categoryDto);
	public CategoryDto getCategoryById(Long id);
	public List<CategoryDto> getall();
	public CategoryDto updateCategory(long id,CategoryDto categoryDto);
	public String deleteCategory(long id);

}
