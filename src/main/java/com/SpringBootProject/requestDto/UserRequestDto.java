package com.SpringBootProject.requestDto;

import java.sql.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequestDto {

	@NotBlank(message = "Please enter first name")
	private String firstName;
	
	private String lastName;

	@Email(message = "Please enter a valid email address")
	@NotBlank(message = "Email can't be empty.Please enter email.")
	private String email;
	
	@NotBlank(message = "Password can't be empty.Please enter password.")
	private String password;
	
	private String address;
	
	private long phoneNumber;
	
	private String birthDate;
	
}
