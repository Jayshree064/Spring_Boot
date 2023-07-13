package com.SpringBootProject.requestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class LoginDto {

	@NotBlank(message = "Email is not empty Please enter email")
	@Email
	private String email;
	
	@Size(min=1,message = "Password is not empty Please enter password")
	private String password;
	
	
}
