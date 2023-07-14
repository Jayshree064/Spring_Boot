package com.SpringBootProject.service;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public LoginResponseDto loginUser(String email,String password,Date deletedAt) {
		Optional<User> user = this.userRepository.findByEmailAndPasswordAndDeletedAt(email, password,deletedAt);
		User userData = user.orElseThrow(() -> new NotFoundException("User not found"));
		return mapper.mapToLoginResponseDto(userData);
	}
}
