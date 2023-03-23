package com.mohit.blog.services;

import com.mohit.blog.payloads.CommentDto;

/**
 * @author Mohit Sindhpure
 * @useClass {CommentService.interface}
 * @apiNote Blogging API
 * @version springframework.boot [2.7.7]
 * @see PackageName, allMethods
 */

public interface CommentService {

	CommentDto createComment(CommentDto commetDto,Integer postId);
	
	void deleteComment(Integer commentId);
}
