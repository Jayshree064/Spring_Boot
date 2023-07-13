package com.SpringBootProject.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.SpringBootProject.dao.UserRepository;
import com.SpringBootProject.entities.User;
import com.SpringBootProject.exception.NotFoundException;
import com.SpringBootProject.mapper.LoginMapper;
import com.SpringBootProject.responseDto.LoginResponseDto;
import com.SpringBootProject.serviceInterface.LoginService;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	LoginMapper mapper;
	
	public ResponseEntity<LoginResponseDto> loginUser(String email,String password,Date deletedAt) {
		LoginResponseDto response = null;
		User user = this.userRepository.findByEmailAndPasswordAndDeletedAt(email, password,deletedAt);
		if(user != null) {
			response = mapper.mapToLoginResponseDto(user);
			return new ResponseEntity<LoginResponseDto>(response,HttpStatus.OK);
		}else {
			throw new NotFoundException("User not found.");
		}
	}
}
