package com.mohit.blog.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Mohit Sindhpure
 * @useClass {CategoryDto.class}
 * @apiNote Blogging API
 * @version springframework.boot [2.7.7]
 * @implNote use Annotations and Validations
 * @see PackageName,FieldName
 * @databaseName blog_app_api
 * @databaseTableName Category
 */
@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

	private Integer categoryId;
	
	@NotEmpty
	@Size(min = 4,message = "Enter Minimum 4 Character !!!")
	private String categoryTitle;
	
	@NotEmpty
	@Size(min = 10,message = "Enter Minimum 10 character !!")
	private String categoryDescription;
}
