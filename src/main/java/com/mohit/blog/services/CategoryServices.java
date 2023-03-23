package com.mohit.blog.services;

import java.util.List;

import com.mohit.blog.payloads.CategoryDto;

/**
 * @author Mohit Sindhpure
 * @useClass {CategoryServices.interface}
 * @apiNote Blogging API
 * @version springframework.boot [2.7.7]
 * @see PackageName, allMethods
 */
public interface CategoryServices {

	CategoryDto createCategory(CategoryDto categoryDto);
	
	CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryID);
	
	void deleteCategory(Integer categoryID);
	
	CategoryDto getCategory(Integer CategoryId);
	
	List<CategoryDto> getAllCategory(Integer pageNumber,Integer pageSize);
	
}
