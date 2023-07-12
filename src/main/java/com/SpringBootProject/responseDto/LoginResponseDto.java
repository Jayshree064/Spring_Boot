package com.SpringBootProject.responseDto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class LoginResponseDto {

	private long userId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String address;
	private long phoneNumber;
	private Date dateOfBirth;
	private Date createdAt;
	private Date updatedAt;
	private Date deletedAt;
	
}
