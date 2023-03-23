package com.mohit.blog.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * @author Mohit Sindhpure
 * @useClass {ApiResponse.class}
 * @apiNote Blogging API
 * @version springframework.boot [2.7.7]
 * @see PackageName,FieldName
 * @use Exception Message
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {

	private String message;
	
	private boolean success;
}
	