package com.SpringBootProject.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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

import com.SpringBootProject.entities.User;
import com.SpringBootProject.requestDto.BlockUserDto;
import com.SpringBootProject.responseDto.BlockUserResponseDto;
import com.SpringBootProject.service.BlockUserServiceImpl;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class BlockUserControllerTest {

	@Mock
	private BlockUserServiceImpl blockUserService;
	
	@InjectMocks
	private BlockUserController blockUserController;
	
	@Mock
	private BindingResult bindingResult;

	@Test
	public void getBlockedUserTest() {
		
		BlockUserDto blockUser = new BlockUserDto();
		blockUser.setEmail("test@gmail.com");
		
		User user = new User();
		user.setEmail("test@gmail.com");
		
		User user1 = new User();
		user.setEmail("tom@gmail.com");
		
		BlockUserResponseDto response = new BlockUserResponseDto();
		response.setBlockedUserId(user1);
		response.setBlockerUserId(user);
	
		List<BlockUserResponseDto> blockUserList = new ArrayList<>();
		blockUserList.add(response);
	
		when(blockUserService.blockUserData("test@gmail.com")).thenReturn(blockUserList);
		
		ResponseEntity<List<BlockUserResponseDto>> expectedResult = new ResponseEntity<List<BlockUserResponseDto>>(blockUserList,HttpStatus.OK);
		
		ResponseEntity<List<BlockUserResponseDto>> actualResult = blockUserController.getBlockedUser(blockUser, bindingResult);
	
		assertThat(actualResult.equals(expectedResult));
	}
}
