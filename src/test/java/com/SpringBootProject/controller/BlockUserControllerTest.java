package com.SpringBootProject.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import com.SpringBootProject.exception.NotFoundException;
import com.SpringBootProject.requestDto.AddBlockUserDto;
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
	
	@Test
	public void removeBlockUserTest() {
		User user = new User();
		user.setEmail("test@gmail.com");
		user.setUserId(1);
		
		User user1 = new User();
		user.setEmail("tom@gmail.com");
		user.setUserId(2);
		
		BlockUserResponseDto blockUser = new BlockUserResponseDto();
		blockUser.setBlockerUserId(user);
		blockUser.setBlockedUserId(user1);
		
		when(blockUserService.removeBlockUser(1)).thenReturn(blockUser);
		
		ResponseEntity<BlockUserResponseDto> expectedResult = new ResponseEntity<BlockUserResponseDto>(blockUser,HttpStatus.OK);
		
		ResponseEntity<BlockUserResponseDto> actualResult = blockUserController.removeBlockUser(1);
		
		assertThat(actualResult.equals(expectedResult));
	}
	
	@Test
	public void addBlockUserTest() {
		AddBlockUserDto blockUserData = new AddBlockUserDto();
		blockUserData.setBlockerUserEmail("jhon@gmail.com");
		blockUserData.setBlockedUserEmail("tom@gmail.com");
		
		User user = new User();
		user.setEmail("jhon@gmail.com");
		user.setUserId(1);
		
		User user1 = new User();
		user.setEmail("tom@gmail.com");
		user.setUserId(2);
		
		BlockUserResponseDto blockUser = new BlockUserResponseDto();
		blockUser.setBlockerUserId(user);
		blockUser.setBlockedUserId(user1);
		
		when(blockUserService.addBlockUser("jhon@gmail.com", "tom@gmail.com")).thenReturn(blockUser);
		
		ResponseEntity<BlockUserResponseDto> expectedResult = new ResponseEntity<BlockUserResponseDto>(blockUser,HttpStatus.OK);
		
		ResponseEntity<BlockUserResponseDto> actualResult = blockUserController.addBlockUser(blockUserData, bindingResult);
		
		assertThat(actualResult.equals(expectedResult));
	}
	
	@Test
	public void blockerAndBlockedUsersAreSameTest() {
		AddBlockUserDto blockUserData = new AddBlockUserDto();
		blockUserData.setBlockerUserEmail("jhon@gmail.com");
		blockUserData.setBlockedUserEmail("jhon@gmail.com");
		
		Exception exception = assertThrows(NotFoundException.class, ()->{blockUserController.addBlockUser(blockUserData, bindingResult);});
		
		String expectedResult = "Blocker and blocked users are same";
		
		assertTrue(exception.getMessage().contains(expectedResult));
	}
}
