package com.SpringBootProject.exception;

import org.springframework.validation.BindingResult;

public class Validations {

	public static void validate(BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
		
			throw new ValidationException(bindingResult);
			
		}
	}
	
}
