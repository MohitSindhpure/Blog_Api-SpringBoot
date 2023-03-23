package com.mohit.blog.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.StreamUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mohit.blog.entities.Post;
import com.mohit.blog.payloads.ApiResponse;
import com.mohit.blog.payloads.PostDto;
import com.mohit.blog.payloads.PostResponse;
import com.mohit.blog.services.FileService;
import com.mohit.blog.services.PostService;
import com.mohit.blog.utils.AppConstant;
import com.mohit.blog.utils.GlobalResourceLogger;

import ch.qos.logback.classic.Logger;

/**
 * @author Mohit Sindhpure
 * @useClass {PostController.class}
 * @apiNote Blogging API
 * @version springframework.boot [2.7.7]
 * @category {Postmapping,Putmapping,DeleteMapping,Getmapping}
 * @implNote use postServiceImpl
 * @see PackageName, allMethods
 * @use Logger
 * 
 */
@RestController
@RequestMapping("/api/")
public class PostController {
	
	private org.slf4j.Logger logger=GlobalResourceLogger.getLogger(PostController.class);
	
	@Autowired
	private FileService fileService;

	@Autowired
	private PostService postService;
	
	@Value("${project.image}")
	private String path;
	//create 
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto,@PathVariable Integer userId,@PathVariable Integer categoryId){
		logger.info("Start Post data create Controller");
		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
		logger.info("End Post data create Controller");
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}
	
	//get by user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUsers(@PathVariable Integer userId){
		
		logger.info("Start getPostByuser data Controller UserId :- " + userId);
		List<PostDto> postByUser = this.postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(postByUser,HttpStatus.OK);	
	}
	
	//get by category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
		logger.info("Start getPostBycategory Controller categoryId :- " + categoryId);
		List<PostDto> byCategory = this.postService.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(byCategory,HttpStatus.OK);
		
	}
	
	// get all posts
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllposts(
			@RequestParam(value = "pageNumber",defaultValue =AppConstant.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = AppConstant.PAGE_SIZE,required = false)Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = AppConstant.SORT_BY,required = false) String sortBy,
			@RequestParam(value = "sortDir",defaultValue = AppConstant.SORT_DIR,required = false) String sortDir){
		logger.info("Start getAll Post data Controller");
		PostResponse allPost = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
				return new ResponseEntity<PostResponse>(allPost,HttpStatus.OK);
		
	}
	//get single post
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getsinglepost(@PathVariable Integer postId){
		logger.info("Start getSinglepost data Controller PostId :- " + postId);
		PostDto postById = this.postService.getPostById(postId);
		logger.info("End getSinglepost data Controller");
		return new ResponseEntity<PostDto>(postById,HttpStatus.OK);
		
	}
	//delete post
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletepost(@PathVariable Integer postId){
		logger.info("Start deleted Post  data Controller");
		logger.warn("Start deleted Post data Controller PostId :- " + postId);
		this.postService.deletePost(postId);
		logger.info("End deleted Post data Controller");
		return new ApiResponse(AppConstant.POST_DELETED,true);		
	}
	
	// update post
	
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatepost(@RequestBody PostDto postDto, @PathVariable Integer postId){
		logger.info("Start Update Post data Controller PostId :- " + postId);
	PostDto updatePost = this.postService.updatePost(postDto, postId);
	logger.info("End Updated Post data Controller");
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	//search
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords)
	{
		logger.info("Start Search Post data Controller PostSearch  :- " + keywords);
		List<PostDto> result = this.postService.searchPosts(keywords);
		logger.info("End Search Post data Controller PostSearch");
		return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
	}
	
	//post image upload
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId
			) throws IOException
	{
		logger.info("Start UploadPostImage Dat Controller PostId :- " + postId);
		PostDto postDto = this.postService.getPostById(postId);
		String fileName = this.fileService.uploadImage(path, image);
		
		postDto.setImageName(fileName);
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		logger.info("End UploadPostImage Dat Controller");
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	
	//method to server file
	@GetMapping(value = "/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException
	{
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		org.springframework.util.StreamUtils.copy(resource,response.getOutputStream());
	}
}
