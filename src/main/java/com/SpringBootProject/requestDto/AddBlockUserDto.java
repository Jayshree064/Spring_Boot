package com.SpringBootProject.requestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data

public class AddBlockUserDto {

	@Email(message = "Please enter valid Email address.")
	@NotEmpty(message = "Blocker user email is not empty.Please enter blocker email")
	private String blockerUserEmail;
	
	@Email(message = "Please enter valid Email address.")
	@NotEmpty(message = "Blocked user email is not empty.Please enter blocked email")
	private String blockedUserEmail;
}
