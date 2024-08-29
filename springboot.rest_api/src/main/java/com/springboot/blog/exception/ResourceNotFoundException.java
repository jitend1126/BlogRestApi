//package com.springboot.blog.exception;
//
//
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//
//@ResponseStatus(value= HttpStatus.NOT_FOUND)
//public class ResourceNotFoundException extends RuntimeException {
//	private String resourceName;
//	private Long fieldValue;
//	private String fieldName;
//	
//	public ResourceNotFoundException(String resourceName, Long fieldValue, String fieldName) {
//		super(String.format("%s is not Found with %s :'%s'",resourceName,fieldValue,fieldName));
//		this.resourceName = resourceName;
//		this.fieldValue = fieldValue;
//		this.fieldName = fieldName;
//	}
//	public String getFieldName() {
//		return fieldName;
//	}
//	public long getFieldValue() {
//		return fieldValue;
//	}
//	public String getResourceName() {
//		return resourceName;
//	}
//	
//	
//
//}
package com.springboot.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    private String resourceName;
    private String fieldName;
    private long fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue)); // Post not found with id : 1
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public long getFieldValue() {
        return fieldValue;
    }
}
