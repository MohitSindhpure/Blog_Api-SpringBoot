package com.mohit.blog.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.Getter;
import lombok.Setter;


/**
 * @author Mohit Sindhpure
 * @useClass {ResourceNotFoundException.class}
 * @apiNote Blogging API
 * @version springframework.boot [2.7.7]
 * @use RuntimeException.class
 * @see PackageName, allMethods
 * 
 */
@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{

	String resourceName;
	String fieldName;
	long fieldvalue;
	public ResourceNotFoundException(String resourceName, String fieldName, long fieldvalue) {
		super(String.format("%s not found with  %s : %s",resourceName,fieldName,fieldvalue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldvalue = fieldvalue;
	}

	
	
}
