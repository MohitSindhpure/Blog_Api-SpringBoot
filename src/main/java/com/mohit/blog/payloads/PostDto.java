package com.mohit.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.mohit.blog.entities.Category;
import com.mohit.blog.entities.Comment;
import com.mohit.blog.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Mohit Sindhpure
 * @useClass {PostDto.class}
 * @apiNote Blogging API
 * @version springframework.boot [2.7.7]
 * @implNote use Annotations and Validations
 * @see PackageName,FieldName
 * @databaseName blog_app_api
 * @databaseTableName Post
 */
@NoArgsConstructor
@Getter
@Setter
public class PostDto {

	@NotEmpty
	@Size(min = 3,message = "Enter minimum 3 character ....")
	private String title;

	@NotEmpty
	@Size(min = 3,message = "Enter minimum 3 character ....")
	private String content;

	
	private String imageName;

	private Date addedDate;
	
	private CategoryDto category;

	private UserDto user;
	
	private Set<CommentDto> comments = new HashSet<>();
}
