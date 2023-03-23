package com.mohit.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohit.blog.entities.Comment;
import com.mohit.blog.entities.Post;
import com.mohit.blog.exceptions.ResourceNotFoundException;
import com.mohit.blog.payloads.CommentDto;
import com.mohit.blog.repository.CommentRepo;
import com.mohit.blog.repository.PostRepo;
import com.mohit.blog.services.CommentService;
import com.mohit.blog.utils.GlobalResourceLogger;


/**
 * @author Mohit Sindhpure
 * @useClass {CommentServiceImpl.class}
 * @apiNote Blogging API
 * @version springframework.boot [2.7.7]
 * @implNote use PostRepo,CommentRepo,ModelMapper
 * @see PackageName, allMethods
 * @use Logger
 * 
 */

@Service
public class CommentServiceImpl implements CommentService {
	
	Logger logger = GlobalResourceLogger.getLogger(CommentServiceImpl.class);
	
	@Autowired
	private PostRepo postRepo;

	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public CommentDto createComment(CommentDto commetDto, Integer postId) {
		logger.info("Initiating Dao call for CreateComment Data CommentServiceImpl postId : - " + postId);
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Post id", postId));
		Comment comment = this.modelMapper.map(commetDto,Comment.class);
		
		comment.setPost(post);
		Comment saveComment = this.commentRepo.save(comment);
		logger.info("Completed Dao call for CreateComment Data Com mentServiceImpl");
		return this.modelMapper.map(saveComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		logger.info("Initiating Dao call for DeleteComment Data CommentServiceImpl commentId : - " + commentId);
		Comment com = this.commentRepo.findById(commentId).orElseThrow(()-> new  ResourceNotFoundException("Comment", "CommentId",commentId));
		this.commentRepo.delete(com);
	}

}
