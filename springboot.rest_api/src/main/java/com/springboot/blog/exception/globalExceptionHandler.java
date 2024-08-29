package com.springboot.blog.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springboot.blog.payload.ErrorDeatils;

@RestControllerAdvice
public class globalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDeatils> handleResourceNotFound(ResourceNotFoundException exception,WebRequest webRequest)
	{
		ErrorDeatils errorDeatils=new ErrorDeatils(null, null, null);
		errorDeatils.setDate(new Date());
		errorDeatils.setMessage(exception.getMessage());
		errorDeatils.setDetails(webRequest.getDescription(false));
		
		
		return new ResponseEntity<ErrorDeatils>(errorDeatils,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(BlogApiException.class)
	public ResponseEntity<ErrorDeatils> handleBlogApiException(BlogApiException exception,WebRequest webRequest)
	{
		ErrorDeatils errorDeatils=new ErrorDeatils(null, null, null);
		errorDeatils.setDate(new Date());
		errorDeatils.setMessage(exception.getMessage());
		errorDeatils.setDetails(webRequest.getDescription(false));
		return new ResponseEntity<ErrorDeatils>(errorDeatils, HttpStatus.BAD_REQUEST);
		
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDeatils> GlobalException(Exception exception,WebRequest webRequest)
	{
		ErrorDeatils errorDeatils=new ErrorDeatils(null, null, null);
		errorDeatils.setDate(new Date());
		errorDeatils.setMessage(exception.getMessage());
		errorDeatils.setDetails(webRequest.getDescription(false));
		return new ResponseEntity<ErrorDeatils>(errorDeatils, HttpStatus.INTERNAL_SERVER_ERROR);
		
		
	}
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex,
			HttpHeaders headers,
			HttpStatusCode status, 
			WebRequest request) {

		Map<String,String> errors=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
		String fieldName=((FieldError)error).getField();
		String message=error.getDefaultMessage();
		errors.put(fieldName, message);
		});
		
		
		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorDeatils> handleAccessDenied(AccessDeniedException accessDeniedException,WebRequest webRequest)
	{
		ErrorDeatils errorDeatils=new ErrorDeatils(null, null, null);
		errorDeatils.setDate(new Date());
		errorDeatils.setMessage(accessDeniedException.getMessage());
		errorDeatils.setDetails(webRequest.getDescription(false));
		return new ResponseEntity<ErrorDeatils>(errorDeatils, HttpStatus.UNAUTHORIZED);
		
	}

}
