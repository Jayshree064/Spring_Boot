package com.SpringBootProject.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBootProject.exception.Validations;
import com.SpringBootProject.requestDto.UserRequestDto;
import com.SpringBootProject.responseDto.LoginResponseDto;
import com.SpringBootProject.responseDto.ResponseDto;
import com.SpringBootProject.serviceInterface.UserService;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public ResponseEntity<List<LoginResponseDto>> getAllUsers() {
		return new ResponseEntity<List<LoginResponseDto>>(this.userService.getAllUsers(),HttpStatus.OK);
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<LoginResponseDto> getUserById(@PathVariable("id") long userId){
		return new ResponseEntity<LoginResponseDto>(this.userService.getUserById(userId),HttpStatus.OK);
	}
	
	@PostMapping("/users")
	public ResponseEntity<ResponseDto> addNewUser(@Valid @RequestBody UserRequestDto userData,BindingResult bindingResult) throws NoSuchAlgorithmException {
		Validations.validate(bindingResult);
		return new ResponseEntity<ResponseDto>(this.userService.addNewUser(userData),HttpStatus.OK);
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<ResponseDto> updateUser(@PathVariable("id") long userId,@Valid @RequestBody UserRequestDto userData,BindingResult bindingResult) throws NoSuchAlgorithmException {
		Validations.validate(bindingResult);
		return new ResponseEntity<ResponseDto>(this.userService.updateUser(userId, userData),HttpStatus.OK);
	}
}
