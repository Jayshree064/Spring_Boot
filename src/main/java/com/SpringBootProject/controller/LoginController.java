package com.SpringBootProject.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBootProject.config.Encryption;
import com.SpringBootProject.requestDto.LoginDto;
import com.SpringBootProject.responseDto.LoginResponseDto;
import com.SpringBootProject.serviceInterface.LoginService;

@RestController
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginData) throws NoSuchAlgorithmException{
		return this.loginService.loginUser(loginData.getEmail(),Encryption.encryptPassword(loginData.getPassword()), null);

	}
	
	
}
