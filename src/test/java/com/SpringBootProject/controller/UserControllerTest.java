package com.SpringBootProject.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.SpringBootProject.requestDto.UserRequestDto;
import com.SpringBootProject.responseDto.LoginResponseDto;
import com.SpringBootProject.responseDto.ResponseDto;
import com.SpringBootProject.service.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

	@Mock
	private UserServiceImpl userService;
	
	@Mock
	private BindingResult bindingResult;
	
	@InjectMocks
	private UserController userController;
	
	@Test
	public void getAllUsersTest() {
		LoginResponseDto userData = new LoginResponseDto();
		userData.setUserId(1);
		userData.setFirstName("Jhon");
		userData.setEmail("jhon@gmail.com");
		
		List<LoginResponseDto> userList = new ArrayList<>();
		userList.add(userData);
		
		when(userService.getAllUsers()).thenReturn(userList);
		
		ResponseEntity<List<LoginResponseDto>> expectedResult = new ResponseEntity<List<LoginResponseDto>>(userList,HttpStatus.OK);
		
		ResponseEntity<List<LoginResponseDto>> actualResult = userController.getAllUsers();
		
		assertThat(actualResult.equals(expectedResult));
	}
	
	@Test
	public void getUserByIdTest() {
		LoginResponseDto userData = new LoginResponseDto();
		userData.setUserId(1);
		userData.setFirstName("Jhon");
		userData.setEmail("jhon@gmail.com");
		
		when(userService.getUserById(1)).thenReturn(userData);
		
		ResponseEntity<LoginResponseDto> expectedResult = new ResponseEntity<LoginResponseDto>(userData,HttpStatus.OK);
		
		ResponseEntity<LoginResponseDto> actualResult = userController.getUserById(1);
		
		assertThat(actualResult.equals(expectedResult));
	}
	
	@Test
	public void addNewUserTest() throws NoSuchAlgorithmException {
		UserRequestDto userData = new UserRequestDto();
		userData.setFirstName("Jhon");
		userData.setEmail("jhon@gmail.com");
		userData.setPassword("1234");
		
		ResponseDto result = new ResponseDto();
		result.setMessage("User added successfully");
		
		when(userService.addNewUser(userData)).thenReturn(result);
		
		ResponseEntity<ResponseDto> expectedResult = new ResponseEntity<ResponseDto>(result,HttpStatus.OK);
		
		ResponseEntity<ResponseDto> actualResult = userController.addNewUser(userData, bindingResult);
		
		assertThat(actualResult.equals(expectedResult));
	}
	
	@Test
	public void updateUserTest() throws NoSuchAlgorithmException {
		UserRequestDto userData = new UserRequestDto();
		userData.setFirstName("Jhon");
		userData.setEmail("jhon@gmail.com");
		userData.setPassword("1234");
		
		ResponseDto result = new ResponseDto();
		result.setMessage("User data updated successfully.");
		
		when(userService.updateUser(1, userData)).thenReturn(result);
		
		ResponseEntity<ResponseDto> expectedResult = new ResponseEntity<ResponseDto>(result,HttpStatus.OK);
		
		ResponseEntity<ResponseDto> actualResult = userController.updateUser(1, userData, bindingResult);
		
		assertThat(actualResult.equals(expectedResult));
	}
	
}
