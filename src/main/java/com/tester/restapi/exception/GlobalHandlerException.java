package com.tester.restapi.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

public class GlobalHandlerException {
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(NotFoundException ex, WebRequest request) {
		ErrorDetailsException ErrorDetailsExc = new ErrorDetailsException(ex.getMessage(), request.getDescription(false), new Date());
		return new ResponseEntity<>(ErrorDetailsExc, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
		ErrorDetailsException ErrorDetailsExc = new ErrorDetailsException(ex.getMessage(), request.getDescription(false), new Date());
		return new ResponseEntity<>(ErrorDetailsExc, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
