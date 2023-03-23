package com.mohit.blog.payloads;

import lombok.Getter;
import lombok.Setter;


/**
 * @author Mohit Sindhpure
 * @useClass {CommentDto.class}
 * @apiNote Blogging API
 * @version springframework.boot [2.7.7]
 * @implNote use Annotations and Validations
 * @see PackageName,FieldName
 * @databaseName blog_app_api
 * @databaseTableName comments
 */

@Setter
@Getter
public class CommentDto {

	private Integer id;
	
	private String content;
}
