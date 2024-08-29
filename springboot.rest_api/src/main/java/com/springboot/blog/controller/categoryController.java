package com.springboot.blog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.payload.CategoryDto;
import com.springboot.blog.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class categoryController {
	
	private CategoryService categoryService;
	public categoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto)
	{
		CategoryDto categoryDto2=categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(categoryDto2, HttpStatus.CREATED);
	}
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getcategoryById(@PathVariable long id)
	{
		CategoryDto categoryDto=categoryService.getCategoryById(id);
		{
			return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);
		}
	}
	@GetMapping
	public ResponseEntity<List<CategoryDto>> getAll()
	{
		List<CategoryDto> categoryDtos=categoryService.getall();
		return new ResponseEntity<>(categoryDtos, HttpStatus.OK);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@PathVariable long id,@RequestBody CategoryDto categoryDto)
	{
		CategoryDto cate=categoryService.updateCategory(id, categoryDto);
		return new ResponseEntity<CategoryDto>(cate, HttpStatus.CREATED);
		
	}
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteById(@PathVariable long id)
	{
		String reString=categoryService.deleteCategory(id);
		return new ResponseEntity<String>(reString, HttpStatus.OK);
	}
	

}
