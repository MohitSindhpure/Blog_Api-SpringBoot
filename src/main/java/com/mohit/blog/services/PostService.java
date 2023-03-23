package com.mohit.blog.services;

import java.util.List;

import com.mohit.blog.payloads.PostDto;
import com.mohit.blog.payloads.PostResponse;

/**
 * @author Mohit Sindhpure
 * @useClass {PostService.interface}
 * @apiNote Blogging API
 * @version springframework.boot [2.7.7]
 * @see PackageName, allMethods
 */


public interface PostService {

	// create/save posts
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	//update post
	PostDto updatePost(PostDto postDto,Integer postId);
	
	//delete
	void deletePost(Integer postId);
	
	//get all post
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	//get single postf
	PostDto getPostById(Integer postId);
	
	//get all posts by Category
	List<PostDto> getPostByCategory(Integer categoryId);
	
	//get all posts by User
	List<PostDto> getPostByUser(Integer userId);
	
	//search posts
	List<PostDto> searchPosts(String keyword);

	
	
}
