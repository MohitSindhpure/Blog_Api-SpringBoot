package com.mohit.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mohit.blog.repository.UserRepo;
import com.mohit.blog.services.UserService;
import com.mohit.blog.services.impl.UserServiceImpl;

@SpringBootTest
class BlogAppApiApplicationTests {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Test
	void contextLoads() {
	}

	@Test
	public void repoTest() {
	
		String classname = this.userRepo.getClass().getName();
		String packageName = this.userRepo.getClass().getPackageName();
		
		System.out.println("This is a Class Name :- " + classname);
		
		System.out.println("This is a Package Name :- " + packageName);
		
	}
	
	@Test
	public void serviceI() {
		String name = this.userService.getClass().getName();
		
		System.out.println("This is a UsersSercice Class Name  :-" + name);
		
		String name2 = this.userServiceImpl.getClass().getName();
		System.out.println("This is a UserServiceImpl Name :-" + name2);
		

	}
}
