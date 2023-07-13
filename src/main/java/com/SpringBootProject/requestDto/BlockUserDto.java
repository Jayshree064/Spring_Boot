package com.SpringBootProject.requestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlockUserDto {

	@Email(message = "Please enter valid email address")
	@NotEmpty(message = "Email is not empty. Please enter email")
	private String email;
	
}
