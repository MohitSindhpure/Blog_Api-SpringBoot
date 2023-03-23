package com.mohit.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mohit.blog.payloads.ApiResponse;
import com.mohit.blog.payloads.CategoryDto;
import com.mohit.blog.payloads.UserDto;
import com.mohit.blog.services.CategoryServices;
import com.mohit.blog.utils.AppConstant;
import com.mohit.blog.utils.GlobalResourceLogger;

/**
 * @author Mohit Sindhpure
 * @useClass {CategoryController.class}
 * @apiNote Blogging API
 * @version springframework.boot [2.7.7]
 * @category {Postmapping,Putmapping,DeleteMapping,Getmapping}
 * @implNote use userServiceImpl
 * @see PackageName, allMethods
 * @use Logger
 * 
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	Logger logger = GlobalResourceLogger.getLogger(CategoryController.class);
	
	@Autowired
	private CategoryServices categoryServices;
	
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createcategory(@Valid @RequestBody CategoryDto categoryDto){
		logger.info("Start Category data create Controller");
		CategoryDto createCategory = this.categoryServices.createCategory(categoryDto);
		logger.info("End Category data create Controller");
		return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);	
	}
	
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer catId){
		logger.info("Start Category data Update Controller CategoryId :- " + catId);
		CategoryDto updateCategory = this.categoryServices.updateCategory(categoryDto, catId);
		logger.info("End Category data Update Controller");
		return new ResponseEntity<CategoryDto>(updateCategory,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId){
		logger.info("Start Category data delete Controller CategoryId :- " + catId);
		this.categoryServices.deleteCategory(catId);
		logger.info("End Category data Deleted Controller");
		return new ResponseEntity<ApiResponse>(new ApiResponse(AppConstant.CATEGORY_DELETED,true),HttpStatus.OK);
	}
	
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer catId){
		logger.info("Start Category data getCategoryById Controller CategoryId :- " + catId);
		return ResponseEntity.ok(this.categoryServices.getCategory(catId));
	}
	 
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getCategoryAll(
			@RequestParam(value = "pagenumber",defaultValue =AppConstant.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam(value = "pagesize",defaultValue = AppConstant.PAGE_SIZE,required = false)Integer pageSize
			){
		logger.info("Start Category data getAll Controller");
		List<CategoryDto> allCategory = this.categoryServices.getAllCategory(pageNumber, pageSize);
		logger.info("End Category data getAll Controller");
		return new ResponseEntity<List<CategoryDto>>(allCategory,HttpStatus.OK);
	}
}
