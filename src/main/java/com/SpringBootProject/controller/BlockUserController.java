package com.SpringBootProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBootProject.exception.Validations;
import com.SpringBootProject.requestDto.BlockUserDto;
import com.SpringBootProject.responseDto.BlockUserResponseDto;
import com.SpringBootProject.serviceInterface.BlockUserService;

import jakarta.validation.Valid;

@RestController
public class BlockUserController {

	@Autowired
	private BlockUserService blockUserService;
	
	@PostMapping("/get")
	public ResponseEntity<List<BlockUserResponseDto>> getBlockedUser(@Valid @RequestBody BlockUserDto user,BindingResult bindingResult){
		Validations.validate(bindingResult);
		return new ResponseEntity<List<BlockUserResponseDto>>(this.blockUserService.blockUserData(user.getEmail()),HttpStatus.OK);
	}
}
