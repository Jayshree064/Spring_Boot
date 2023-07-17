package com.SpringBootProject.exception;


import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private Set<String> errors;
	
	public void addError(String error) {
		this.errors.add(error);
	}

	public ValidationException() {
		super();
	}

	public ValidationException(BindingResult bindingResult) {
		super("Validations failed");
		this.errors = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toSet());
	}
	
	public ValidationException(String message) {
		super(message);
	}
}