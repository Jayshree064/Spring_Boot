package com.SpringBootProject.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.SpringBootProject.requestDto.LoginDto;
import com.SpringBootProject.responseDto.LoginResponseDto;
import com.SpringBootProject.service.LoginServiceImpl;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class LoginControllerTest {

	@Mock
	private LoginServiceImpl loginService;
	
	@InjectMocks
	private LoginController loginController;

	@Mock
	private BindingResult bindingResult;
	
	@Test
	public void loginTest() throws NoSuchAlgorithmException {
	
		LoginDto loginDto = new LoginDto();
		loginDto.setEmail("test@gmail.com");
		loginDto.setPassword("123456");
		
		LoginResponseDto loginResponse = new LoginResponseDto();
		loginResponse.setEmail("test@gmail.com");
		loginResponse.setPassword("8d969eef6ecad3c29a3a629280e686cfc3f5d5a86aff3ca122c923adc6c92");
	
		when(loginService.loginUser(loginDto.getEmail(), loginDto.getPassword(), null)).thenReturn(loginResponse);
		
		ResponseEntity<LoginResponseDto> expectedResult = new ResponseEntity<LoginResponseDto>(loginResponse, HttpStatus.OK);
		
		ResponseEntity<LoginResponseDto> actualResult = loginController.login(loginDto, bindingResult);
		
		assertThat(actualResult.equals(expectedResult));
		
	}
	
}
