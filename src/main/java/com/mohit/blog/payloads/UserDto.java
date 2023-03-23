package com.mohit.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



/**
 * @author Mohit Sindhpure
 * @useClass {UserDto.class}
 * @apiNote Blogging API
 * @version springframework.boot [2.7.7]
 * @implNote use Annotations and Validations
 * @see PackageName,FieldName
 * @databaseName blog_app_api
 * @databaseTableName User
 */
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class UserDto {
	
	private int id;

	@NotEmpty
	private String name;

	@Email(message = "Email address is not valid!!")
	private String email;

	@NotEmpty()
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
	private String password;

	@NotNull
	private String about;
}
