package com.coffeehouse.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CoffeeItemAlreadyExistsException extends RuntimeException{

	public CoffeeItemAlreadyExistsException(String message) {
		super(message);
	}

	
}
