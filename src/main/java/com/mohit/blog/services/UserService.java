package com.mohit.blog.services;

import java.util.List;


import com.mohit.blog.payloads.UserDto;

/**
 * @author Mohit Sindhpure
 * @useClass {UserService.interface}
 * @apiNote Blogging API
 * @version springframework.boot [2.7.7]
 * @see PackageName, allMethods
 */

public interface UserService {
	
	UserDto registerNewUser(UserDto user);

	UserDto createUser(UserDto userDto);
	
	
	  UserDto updateUser(UserDto userDto,Integer userId);
	  
	  UserDto getUserById(Integer userId);
	  
	  List<UserDto> getAllUsers(Integer pageNumber,Integer pageSize);
	  
	  void deleteUser(Integer userId);
	 
}
