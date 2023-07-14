package com.SpringBootProject.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBootProject.config.Encryption;
import com.SpringBootProject.exception.Validations;
import com.SpringBootProject.requestDto.LoginDto;
import com.SpringBootProject.responseDto.LoginResponseDto;
import com.SpringBootProject.serviceInterface.LoginService;

import jakarta.validation.Valid;


@RestController
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginDto loginData,BindingResult bindingResult) throws NoSuchAlgorithmException{

		Validations.validate(bindingResult);
	
		return new ResponseEntity<LoginResponseDto>(this.loginService.loginUser
				(loginData.getEmail(),Encryption.encryptPassword(loginData.getPassword()), null),
				HttpStatus.OK);
	}
	
	
}
