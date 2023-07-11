package com.SpringBootProject.responseDto;

public class ResponseDto {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ResponseDto(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "ResponseDto [message=" + message + "]";
	}
}
