package com.mohit.blog.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mohit.blog.payloads.ApiResponse;
import com.mohit.blog.payloads.UserDto;
import com.mohit.blog.services.UserService;
import com.mohit.blog.utils.AppConstant;
import com.mohit.blog.utils.GlobalResourceLogger;

import ch.qos.logback.classic.Logger;

/**
 * @author Mohit Sindhpure
 * @useClass {UserController.class}
 * @apiNote Blogging API
 * @version springframework.boot [2.7.7]
 * @category {Postmapping,Putmapping,DeleteMapping,Getmapping}
 * @implNote use userServiceImpl
 * @see PackageName, allMethods
 * @use Logger
 * 
 */

@RestController
@RequestMapping("/api/users")
public class UserController {

	private org.slf4j.Logger logger = GlobalResourceLogger.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {

		logger.info("Start User data Storing Controller");

		UserDto createUser = this.userService.createUser(userDto);

		logger.info("Completed User Data Enter Controller");
		return new ResponseEntity<>(createUser, HttpStatus.CREATED);
	}
	// ADMIN
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,
			@PathVariable("userId") Integer uid) {
		logger.info("Start User data FindById Controller UserId :- " + uid);

		UserDto updateUser = this.userService.updateUser(userDto, uid);
		logger.info("End User data FindById Controller");

		return ResponseEntity.ok(updateUser);
	}
	

	@DeleteMapping("/{userId}") 
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid) {
		
		logger.info("Start Deleting Controller" );
		logger.warn("Data Are Deleted. UserId :- " + uid);
		this.userService.deleteUser(uid);

		// return new ResponseEntity(Map.of("message", "User Deleted Successfully"),
		// HttpStatus.OK);
		logger.info("End Deleting Controller");
		return new ResponseEntity<ApiResponse>(new ApiResponse(AppConstant.USER_DELETED, true), HttpStatus.OK);
	}

	@GetMapping("/user")
	public ResponseEntity<List<UserDto>> getAllUsers(
			@RequestParam(value = "pageNumber",defaultValue = AppConstant.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = AppConstant.PAGE_SIZE,required = false)Integer pageSize
			) {
		  
		logger.info("Start Get AllData Controller");
		List<UserDto> allUsers = this.userService.getAllUsers(pageNumber, pageSize);
		return new ResponseEntity<List<UserDto>>(allUsers,HttpStatus.OK);

	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSinglUser(@PathVariable("userId") Integer uid) {
		logger.info("Start getSingleData Controller UserId :- " + uid);
	
		
		return ResponseEntity.ok(this.userService.getUserById(uid));
		
	}
}
