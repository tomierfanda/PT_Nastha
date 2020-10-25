package com.tester.restapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends Exception {
	
	public NotFoundException(String pesan) {
		// TODO Auto-generated constructor stub
		super(pesan);
	}
}
