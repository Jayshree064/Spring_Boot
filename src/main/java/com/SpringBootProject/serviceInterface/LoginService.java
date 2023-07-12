package com.SpringBootProject.serviceInterface;

import java.sql.Date;

import org.springframework.http.ResponseEntity;

import com.SpringBootProject.responseDto.LoginResponseDto;

public interface LoginService {

	ResponseEntity<LoginResponseDto> loginUser(String email,String password,Date deletedAt);
}
