package com.SpringBootProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBootProject.exception.ErrorResponse;
import com.SpringBootProject.exception.NotFoundException;
import com.SpringBootProject.exception.ValidationErrorResponse;
import com.SpringBootProject.exception.Validations;
import com.SpringBootProject.requestDto.AddBlockUserDto;
import com.SpringBootProject.requestDto.BlockUserDto;
import com.SpringBootProject.responseDto.BlockUserResponseDto;
import com.SpringBootProject.serviceInterface.BlockUserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@RestController
public class BlockUserController {

	@Autowired
	private BlockUserService blockUserService;
	
	@GetMapping("/block-users")
	public ResponseEntity<List<BlockUserResponseDto>> getBlockedUser(@Valid @RequestBody BlockUserDto user,BindingResult bindingResult){
		Validations.validate(bindingResult);
		return new ResponseEntity<List<BlockUserResponseDto>>(this.blockUserService.blockUserData(user.getEmail()),HttpStatus.OK);
	}
	
	@PostMapping("/block-users")
	public ResponseEntity<BlockUserResponseDto> addBlockUser(@Valid @RequestBody AddBlockUserDto user,BindingResult bindingResult) {
		Validations.validate(bindingResult);
		if(user.getBlockedUserEmail().equals(user.getBlockerUserEmail())) {
			throw new NotFoundException("Blocker and blocked users are same");
		}
		return new ResponseEntity<BlockUserResponseDto>(this.blockUserService.addBlockUser(
				user.getBlockerUserEmail(), user.getBlockedUserEmail()),HttpStatus.OK);
	}
	
	@DeleteMapping("/block-users/{id}")
	public ResponseEntity<BlockUserResponseDto> removeBlockUser(@PathVariable("id") long id){
		return new ResponseEntity<BlockUserResponseDto>(this.blockUserService.removeBlockUser(id),HttpStatus.OK);
	}
	
}
