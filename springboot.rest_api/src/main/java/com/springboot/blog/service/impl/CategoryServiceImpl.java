package com.springboot.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.springboot.blog.entity.category;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.CategoryDto;
import com.springboot.blog.repository.categoryRepository;
import com.springboot.blog.repository.postRepository;
import com.springboot.blog.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {
	
	private categoryRepository categoryRepository;
	private postRepository postRepository;
	private ModelMapper mapper;

	public CategoryServiceImpl(com.springboot.blog.repository.categoryRepository categoryRepository,
			com.springboot.blog.repository.postRepository postRepository, ModelMapper mapper) {
		super();
		this.categoryRepository = categoryRepository;
		this.postRepository = postRepository;
		this.mapper = mapper;
	}

	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		category category=mapTocategory(categoryDto);
		category category2=categoryRepository.save(category);
		CategoryDto categoryDto2;
		categoryDto2=mapToDto(category2);
		// TODO Auto-generated method stub
		return categoryDto2;
	}
	private CategoryDto mapToDto(category category)
	{
		CategoryDto categoryDto=mapper.map(category, CategoryDto.class);
		return categoryDto;
	}
	private category mapTocategory(CategoryDto categoryDto)
	{
		category category=mapper.map(categoryDto, category.class);
		return category;
	}


	@Override
	public CategoryDto getCategoryById(Long id) {
		category category=categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category", "id", id));
		// TODO Auto-generated method stub
		return mapToDto(category);
		
	}


	@Override
	public List<CategoryDto> getall() {
		List<category> categories=categoryRepository.findAll();
		List<CategoryDto> resCategoryDtos=categories.stream().map((category)->mapToDto(category)).collect(Collectors.toList());
		// TODO Auto-generated method stub
		return resCategoryDtos;
	}


	@Override
	public CategoryDto updateCategory(long id, CategoryDto categoryDto) {
		category category=categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category", "id", id));
		category.setDescription(categoryDto.getDescription());
		category.setName(categoryDto.getName());
		category category2=categoryRepository.save(category);
		return mapToDto(category2);
		
		
		// TODO Auto-generated method stub
	}


	@Override
//	
	public String deleteCategory(long id) {
		category category=categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category", "id", id));
		
		categoryRepository.deleteById(id);
		
		return "Category deleted Successfully";
		
		// TODO Auto-generated method stub
	
	}

}
