package com.mohit.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mohit.blog.payloads.ApiResponse;

/**
 * @author Mohit Sindhpure
 * @useClass {GlobalExceptionHandler.class}
 * @apiNote Blogging API
 * @version springframework.boot [2.7.7]
 * @implNote use ResourceNotFoundException.class
 * @Annotation Use [@RestControllerAdvice,@ExceptionHandler]
 * @see PackageName, allMethods
 * 
 */

@RestControllerAdvice
public class GlobalExceptionHandler {


	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException ex){
		String message = ex.getMessage(); 
		ApiResponse apiResponse = new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex){
		
		Map<String, String> resp = new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldname = ((FieldError)error).getField();
			String message = error.getDefaultMessage();
			resp.put(fieldname, message);
		});
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ApiResponse> handleApiException(ApiException ex){
		String message = ex.getMessage(); 
		ApiResponse apiResponse = new ApiResponse(message,true);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
	}
	
}
