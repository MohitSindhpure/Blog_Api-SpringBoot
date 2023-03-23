package com.mohit.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mohit.blog.entities.Category;
import com.mohit.blog.exceptions.ResourceNotFoundException;
import com.mohit.blog.payloads.CategoryDto;
import com.mohit.blog.repository.CategoryRepo;
import com.mohit.blog.services.CategoryServices;
import com.mohit.blog.utils.GlobalResourceLogger;

import net.bytebuddy.asm.Advice.This;
/**
 * @author Mohit Sindhpure
 * @useClass {CategoryServiceImpl.class}
 * @apiNote Blogging API
 * @version springframework.boot [2.7.7]
 * @implNote use categoryService,CategoryRepo,ModelMapper
 * @see PackageName, allMethods
 * @use Logger
 * 
 */
@Service
public class CategoryServiceImpl implements CategoryServices {

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	Logger logger = GlobalResourceLogger.getLogger(CategoryServiceImpl.class);
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		logger.info("Initiating Dao call for Create CategoryServiceImpl");
		Category category = this.modelMapper.map(categoryDto, Category.class);
		Category save = this.categoryRepo.save(category);
		logger.info("Completed Dao call for Create CategoryServiceImpl");
		return this.modelMapper.map(save,CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryID) {
		logger.info("Initiating Dao call for Update CategoryServiceImpl CategoryId :- " + categoryID);
		Category cat = this.categoryRepo.findById(categoryID).orElseThrow(()-> new ResourceNotFoundException("Category","Category Id", categoryID));
		
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category save = this.categoryRepo.save(cat);
		logger.info("Completed Dao call for Update CategoryServiceImpl");
		return this.modelMapper.map(save, categoryDto.getClass());
	}

	@Override
	public void deleteCategory(Integer categoryID) {
		logger.info("Initiating Dao call for Deleted CategoryServiceImpl CategoryId :- " + categoryID);
		Category cat = this.categoryRepo.findById(categoryID).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryID));
		this.categoryRepo.delete(cat);
	}

	@Override
	public CategoryDto getCategory(Integer CategoryId) {
		logger.info("Initiating Dao call for getCategory CategoryServiceImpl CategoryId :- " + CategoryId);
		Category cat = this.categoryRepo.findById(CategoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category Id", CategoryId));
		logger.info("Completed Dao call for getCategory CategoryServiceImpl");
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory(Integer pageNumber,Integer pageSize) {
		logger.info("Initiating Dao call for getAllCategory CategoryServiceImpl");
		PageRequest request = PageRequest.of(pageNumber, pageSize);
		Page<Category> findAll = this.categoryRepo.findAll(request);
		List<Category> category = findAll.getContent();
		List<CategoryDto> collect = category.stream().map((cat) -> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		logger.info("Completed Dao call for getAllCategory CategoryServiceImpl");
		return collect;
	}

}
