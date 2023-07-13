package com.SpringBootProject.exception;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorResponse handleException(UserNotFoundException exception) {
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(),exception.getMessage());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ValidationException.class)
	@ResponseBody
	public ValidationErrorResponse handleBadRequestException(ValidationException exception) {
		Set<String> errors = exception.getErrors();
		return new ValidationErrorResponse(HttpStatus.BAD_REQUEST.value(),errors);
	}

}
