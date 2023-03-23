package com.mohit.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mohit.blog.entities.Category;
import com.mohit.blog.entities.Post;
import com.mohit.blog.entities.User;
import com.mohit.blog.exceptions.ResourceNotFoundException;
import com.mohit.blog.payloads.PostDto;
import com.mohit.blog.payloads.PostResponse;
import com.mohit.blog.repository.CategoryRepo;
import com.mohit.blog.repository.PostRepo;
import com.mohit.blog.repository.UserRepo;
import com.mohit.blog.services.PostService;
import com.mohit.blog.utils.GlobalResourceLogger;

import net.bytebuddy.asm.Advice.OffsetMapping.Sort;
import net.bytebuddy.asm.Advice.This;

/**
 * @author Mohit Sindhpure
 * @useClass {PostServiceImpl.class}
 * @apiNote Blogging API
 * @version springframework.boot [2.7.7]
 * @implNote use PostService,PostRepo,ModelMapper
 * @see PackageName, allMethods
 * @use Logger
 * 
 */
@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	Logger logger = GlobalResourceLogger.getLogger(PostServiceImpl.class);

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		logger.info("Initiating Dao call for Create Data PostServiceImpl");

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category Id", categoryId));

		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);

		Post newpostPost = this.postRepo.save(post);
		logger.info("Completed Dao call for Create Data PostServiceImpl");
		return this.modelMapper.map(newpostPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		logger.info("Initiating Dao call for Update Data PostServiceImpl PostId :- " + postId);
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));

		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		Post save = this.postRepo.save(post);
		logger.info("Completed Dao call for Update Data PostServiceImpl");
		return this.modelMapper.map(save, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		logger.info("Initiating Dao call for Deleted Data PostServiceImpl PostId :- " + postId);
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId Id", postId));
		logger.info("Completed Dao call for Deleted Data PostServiceImpl");
		this.postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		logger.info("Initiating Dao call for GEtAll Data PostServiceImpl");
		// this is turnary operator org.springframework.data.domain.Sort
		// sort=sortDir.equalsIgnoreCase("asc")? sort =
		// org.springframework.data.domain.Sort.by(sortBy).ascending():sort =
		// org.springframework.data.domain.Sort.by(sortBy).descending();
		org.springframework.data.domain.Sort sort = null;
		if (sortDir.equalsIgnoreCase("asc")) {
			sort = org.springframework.data.domain.Sort.by(sortBy).ascending();
		} else {
			sort = org.springframework.data.domain.Sort.by(sortBy).descending();
		}
		PageRequest p = PageRequest.of(pageNumber, pageSize, sort);

		Page<Post> pagepost = this.postRepo.findAll(p);
		List<Post> allPosts = pagepost.getContent();
		List<PostDto> collect = allPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();

		postResponse.setContent(collect);
		postResponse.setPageNumber(pagepost.getNumber());
		postResponse.setPageSize(pagepost.getSize());
		postResponse.setTotalElements(pagepost.getTotalElements());

		postResponse.setTotalPages(pagepost.getTotalPages());
		postResponse.setLastPage(pagepost.isLast());
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		logger.info("Initiating Dao call for getPostById Data PostServiceImpl PostId :- " + postId);
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
		logger.info("Completed Dao call for getPostById Data PostServiceImpl");
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		logger.info("Initiating Dao call for getPostByCategory Data PostServiceImpl CategoryId :- " + categoryId);
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
		List<Post> posts = this.postRepo.findByCategory(cat);
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		logger.info("Completed Dao call for getPostByCategory Data PostServiceImpl");
		return postDtos;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		logger.info("Initiating Dao call for getPostByUser Data PostServiceImpl UserId :- " + userId);
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));
		List<Post> posts = this.postRepo.findByUser(user);
		List<PostDto> postDtos = posts.stream().map((Post) -> this.modelMapper.map(Post, PostDto.class))
				.collect(Collectors.toList());
		logger.info("Completed Dao call for getPostByUser Data PostServiceImpl");
		return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		logger.info("Initiating Dao call for getSearchPosts Data PostServiceImpl  :- " + keyword);
			List<Post> posts = this.postRepo.searchByTitle("%"+keyword+"%");
			List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
			logger.info("Completed Dao call for getSearchPosts Data PostServiceImpl");
			return postDtos;
	}

}
