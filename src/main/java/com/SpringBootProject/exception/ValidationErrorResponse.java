package com.SpringBootProject.exception;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationErrorResponse {

	private int statusCode;
	
	private Set<String> message;

	public ValidationErrorResponse(Set<String> message) {
		super();
		this.message = message;
	}

	
	
}
