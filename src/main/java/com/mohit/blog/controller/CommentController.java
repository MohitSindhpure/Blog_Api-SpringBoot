package com.mohit.blog.controller;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mohit.blog.entities.Comment;
import com.mohit.blog.payloads.ApiResponse;
import com.mohit.blog.payloads.CommentDto;
import com.mohit.blog.services.CommentService;
import com.mohit.blog.utils.GlobalResourceLogger;

/**
 * @author Mohit Sindhpure
 * @useClass {CommentController.class}
 * @apiNote Blogging API
 * @version springframework.boot [2.7.7]
 * @category {Postmapping,DeleteMapping}
 * @implNote use CommentService
 * @see PackageName, allMethods
 * @use Logger
 * 
 */
@RestController
@RequestMapping("/api")
public class CommentController {
	
	Logger logger = GlobalResourceLogger.getLogger(CommentController.class);
	
	@Autowired
	private CommentService commentService;

	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment,@PathVariable Integer postId){
		logger.info("Start Comment data create Controller PostId :- " + postId);
		CommentDto createComment = this.commentService.createComment(comment, postId);
		logger.info("End Comment data create Controller");
		return new ResponseEntity<CommentDto>(createComment,HttpStatus.CREATED);
}
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
		logger.info("Start Comment data Deleted Controller CommentId :- " + commentId);
		this.commentService.deleteComment(commentId);
		logger.info("End Comment data Deleted Controller");
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted Successfully !!",true),HttpStatus.OK);

	}
}
