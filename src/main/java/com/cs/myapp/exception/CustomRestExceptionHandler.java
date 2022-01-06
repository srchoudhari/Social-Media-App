package com.cs.myapp.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(AppException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorMsg> generalExceptionHandler(AppException ex){
		ErrorMsg error = new ErrorMsg(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), new Date(),ex.getMessage());
		return new ResponseEntity<ErrorMsg>(error, error.getStatus());
	}
}
