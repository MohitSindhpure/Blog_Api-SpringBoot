package com.mohit.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mohit.blog.entities.Role;
import com.mohit.blog.entities.User;
import com.mohit.blog.exceptions.ResourceNotFoundException;
import com.mohit.blog.payloads.UserDto;
import com.mohit.blog.repository.RoleRepo;
import com.mohit.blog.repository.UserRepo;
import com.mohit.blog.services.UserService;
import com.mohit.blog.utils.AppConstant;

import ch.qos.logback.classic.Logger;
import net.bytebuddy.asm.Advice.This;

/**
 * @author Mohit Sindhpure
 * @useClass {UserServiceImpl.class}
 * @apiNote Blogging API
 * @version springframework.boot [2.7.7]
 * @implNote use UserServices,UserRepo,ModelMapper
 * @see PackageName, allMethods
 * @use Logger
 * 
 */
@Service
public class UserServiceImpl implements UserService {
	
	org.slf4j.Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;

	@Override
	public UserDto createUser(UserDto userDto) {
		 
		logger.info("Initiating Dao call for save user UserServiceImpl");
		
		User user = this.dtoToUser(userDto);
		User saveUser = this.userRepo.save(user);
		
		logger.info("Completed dao call for save user UserServiceImpl");
		
		return this.userToDto(saveUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		
		logger.info("Initiating Dao call for Update user UserServiceImpl");

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());

		User updateUser = this.userRepo.save(user);

		UserDto userToDto = this.userToDto(updateUser);

		logger.info("Completed Dao call for Update user UserServiceImpl");
		
		return userToDto;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		logger.info("Initiating Dao call for GetUserById user");		

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		logger.info("Completed Dao call for GetUserById user UserServiceImpl");
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers(Integer pageNumber,Integer pageSize) {
		logger.info("Initiating Dao call for GetAllUsers UserServiceImpl");	
		PageRequest request = PageRequest.of(pageNumber,pageSize);
	 Page<User> page = this.userRepo.findAll(request);
	 List<User> alluser = page.getContent();
		List<UserDto> userlist = alluser.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		logger.info("Completed Dao call for GetAllUsers UserServiceImpl");
		return userlist; 
	}

	@Override
	public void deleteUser(Integer userId) {
		logger.info("Initiating Dao call for deleteUser UserServiceImpl");
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		this.userRepo.delete(user);
		
		logger.info("Completed Dao call for deleteUser UserServiceImpl");
	}

	public User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto,User.class);
		
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
		
		return user;
	}

	public UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setAbout(user.getAbout());
//		userDto.setPassword(user.getPassword());
		
		return userDto;

	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		UserDto user = this.modelMapper.map(userDto, UserDto.class);
		//encoded the password
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		//roles
		Role role = this.roleRepo.findById(AppConstant.NORMAL_USER).get();
		
		return null;
	}
}
