package com.SpringBootProject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.SpringBootProject.dao.BlockUserRepository;
import com.SpringBootProject.dao.UserRepository;
import com.SpringBootProject.entities.BlockUser;
import com.SpringBootProject.entities.User;
import com.SpringBootProject.exception.NotFoundException;
import com.SpringBootProject.mapper.BlockUserMapper;
import com.SpringBootProject.responseDto.BlockUserResponseDto;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class BlockUserServiceTest {

	@Mock
	private BlockUserRepository blockUserRepo;
	
	@InjectMocks
	private BlockUserServiceImpl blockUserService;
	
	@Mock
	private BlockUserMapper mapper;
	
	@Test
	public void getBlockUserListTest() {
		
		User user = new User();
		user.setUserId(1L);
		user.setEmail("test@gmail.com");
		
		User user1 = new User();
		user1.setUserId(2);
		user1.setEmail("abc@gmail.com");
		
		BlockUser blockUser = new BlockUser();
		blockUser.setBlockedUserId(user1);
		blockUser.setBlockerUserId(user);
		
		List<BlockUser> userList = new ArrayList<>();
		userList.add(blockUser);
		
		when(blockUserRepo.findAllBlockUsers("test@gmail.com")).thenReturn(userList);
		
		BlockUserResponseDto blockUserResponse = new BlockUserResponseDto();
		blockUserResponse.setBlockedUserId(user1);
		blockUserResponse.setBlockerUserId(user);
		
		List<BlockUserResponseDto> blockUserList = new ArrayList<>();
		blockUserList.add(blockUserResponse);
		
		when(mapper.mapToResponse(userList)).thenReturn(blockUserList);

		assertEquals(blockUserService.blockUserData("test@gmail.com"), blockUserList);
	}
	
	@Test
	public void emptyBlockUserListTest() {
		
		User user = new User();
		user.setUserId(1);
		user.setEmail("test@gmail.com");
	
		User user1 = new User();
		user1.setUserId(2);
		user1.setEmail("abc@gmail.com");
		
		BlockUser blockUser = new BlockUser();
		blockUser.setBlockedUserId(user1);
		blockUser.setBlockerUserId(user);
		
		List<BlockUser> userList = new ArrayList<>();
		userList.add(blockUser);
		
		when(blockUserRepo.findAllBlockUsers("test@gmail.com")).thenReturn(userList);
		
		Exception exception = assertThrows(NotFoundException.class, () -> {blockUserService.blockUserData("admin@gmail.com");});
	
		String expectedMessage = "User block list not found";
		
		assertTrue(exception.getMessage().contains(expectedMessage));
	}
}
