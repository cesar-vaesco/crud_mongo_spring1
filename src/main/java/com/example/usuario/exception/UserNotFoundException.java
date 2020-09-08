package com.example.usuario.exception;

import org.springframework.core.NestedRuntimeException;

public class UserNotFoundException extends NestedRuntimeException {

	 public UserNotFoundException(String userId) {
	        super(String.format("User with  Id '%s' not founded", userId));
	    }
	  
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	

	    
}
