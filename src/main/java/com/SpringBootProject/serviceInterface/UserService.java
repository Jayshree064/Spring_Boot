package com.SpringBootProject.serviceInterface;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.SpringBootProject.requestDto.UserRequestDto;
import com.SpringBootProject.responseDto.LoginResponseDto;
import com.SpringBootProject.responseDto.ResponseDto;

public interface UserService {

	List<LoginResponseDto> getAllUsers();
	LoginResponseDto getUserById(long userId);
	ResponseDto addNewUser(UserRequestDto userData) throws NoSuchAlgorithmException;
	ResponseDto updateUser(long userId,UserRequestDto userData) throws NoSuchAlgorithmException;
}
