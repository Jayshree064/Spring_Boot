package com.SpringBootProject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	
	@Mock
	private UserRepository userRepo;
	
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
	
	@Test
	public void removeBlockUserTest() {
		User user = new User();
		user.setUserId(1);
		user.setEmail("test@gmail.com");
	
		User user1 = new User();
		user1.setUserId(2);
		user1.setEmail("abc@gmail.com");
		
		Optional<BlockUser> blockUserData = Optional.of(new BlockUser());
		blockUserData.get().setBlockedUserId(user1);
		blockUserData.get().setBlockerUserId(user);
		
		when(blockUserRepo.findByBlockUserIdAndDeletedAt(2, null)).thenReturn(blockUserData);
			
		blockUserData.get().setDeletedAt(new Date(System.currentTimeMillis()));
				
		BlockUserResponseDto expectedResult = new BlockUserResponseDto();
		expectedResult.setBlockedUserId(user1);
		expectedResult.setBlockerUserId(user);
		expectedResult.setDeletedAt(new Date(System.currentTimeMillis()));
		
		when(mapper.mapBlockUserToResponseDto(blockUserData.get())).thenReturn(expectedResult);
		
		assertEquals(blockUserService.removeBlockUser(2), expectedResult);
	}
	
	@Test
	public void userNotFoundTest() {
		User user = new User();
		user.setUserId(1);
		user.setEmail("test@gmail.com");
	
		User user1 = new User();
		user1.setUserId(2);
		user1.setEmail("abc@gmail.com");
		
		Optional<BlockUser> blockUserData = Optional.of(new BlockUser());
		blockUserData.get().setBlockedUserId(user1);
		blockUserData.get().setBlockerUserId(user);
		
		when(blockUserRepo.findByBlockUserIdAndDeletedAt(2, null)).thenReturn(blockUserData);
		
		Exception exception = assertThrows(NotFoundException.class, () -> {blockUserService.removeBlockUser(3L);});
		
		String expectedMessage = "User not found";
		
		assertTrue(exception.getMessage().contains(expectedMessage));
	}
	
	@Test
	public void addBlockUserTest() {
		Optional<User> blockerUser = Optional.of(new User());
		blockerUser.get().setUserId(1);
		blockerUser.get().setEmail("jhon@gmail.com");
		
		when(userRepo.findByEmailAndDeletedAt("jhon@gmail.com", null)).thenReturn(blockerUser);
		
		Optional<User> blockedUser = Optional.of(new User());
		blockedUser.get().setUserId(2);
		blockedUser.get().setEmail("tom@gmail.com");
		
		when(userRepo.findByEmailAndDeletedAt("tom@gmail.com", null)).thenReturn(blockedUser);
		
		BlockUser blockUserData = new BlockUser();
		blockUserData.setBlockerUserId(blockerUser.get());
		blockUserData.setBlockedUserId(blockedUser.get());
		blockUserData.setCreatedAt(new Date(System.currentTimeMillis()));
		
		when(blockUserRepo.save(blockUserData)).thenReturn(blockUserData);
		
		BlockUserResponseDto expectedResult = new BlockUserResponseDto();
		expectedResult.setBlockerUserId(blockerUser.get());
		expectedResult.setBlockedUserId(blockedUser.get());
		expectedResult.setCreatedAt(new Date(System.currentTimeMillis()));
		
		when(mapper.mapBlockUserToResponseDto(org.mockito.ArgumentMatchers.any())).thenReturn(expectedResult);
		
		assertEquals(blockUserService.addBlockUser("jhon@gmail.com", "tom@gmail.com"), expectedResult);
	}
	
	@Test
	public void blockerUserNotFoundTest() {
		Optional<User> blockerUser = Optional.empty();
		
		when(userRepo.findByEmailAndDeletedAt("jhon@gmail.com", null)).thenReturn(blockerUser);
		
		Exception exception = assertThrows(NotFoundException.class, () -> {blockUserService.addBlockUser("jhon@gmail.com", "tom@gmail.com");});
		
		String expectedResult = "Blocker User not found";
		
		assertTrue(exception.getMessage().contains(expectedResult));
	}
	
	@Test
	public void blockedUserNotFoundTest() {
		Optional<User> blockerUser = Optional.of(new User());
		blockerUser.get().setUserId(1);
		blockerUser.get().setEmail("jhon@gmail.com");
		
		when(userRepo.findByEmailAndDeletedAt("jhon@gmail.com", null)).thenReturn(blockerUser);
		
		Optional<User> blockedUser = Optional.empty();
		
		when(userRepo.findByEmailAndDeletedAt("tom@gmail.com", null)).thenReturn(blockedUser);
		
		Exception exception = assertThrows(NotFoundException.class, () -> {blockUserService.addBlockUser("jhon@gmail.com", "tom@gmail.com");});
		
		String expectedResult = "Blocked User not found";
		
		assertTrue(exception.getMessage().contains(expectedResult));
	}
	
	@Test
	public void userAlreadyBlockTest() {
		Optional<User> blockerUser = Optional.of(new User());
		blockerUser.get().setUserId(1);
		blockerUser.get().setEmail("jhon@gmail.com");
		
		when(userRepo.findByEmailAndDeletedAt("jhon@gmail.com", null)).thenReturn(blockerUser);
		
		Optional<User> blockedUser = Optional.of(new User());
		blockedUser.get().setUserId(2);
		blockedUser.get().setEmail("tom@gmail.com");
		
		when(userRepo.findByEmailAndDeletedAt("tom@gmail.com", null)).thenReturn(blockedUser);
		
		BlockUser blockUser = new BlockUser();
		blockUser.setBlockedUserId(blockedUser.get());
		blockUser.setBlockerUserId(blockerUser.get());
		
		when(blockUserRepo.findByBlockedUserIdAndDeletedAt(blockedUser.get(), null)).thenReturn(blockUser);
		
		Exception exception = assertThrows(NotFoundException.class, () -> {blockUserService.addBlockUser("jhon@gmail.com", "tom@gmail.com");});
		
		String expectedResult = "User already blocked";
		
		assertTrue(exception.getMessage().contains(expectedResult));
	}
	
	@Test
	public void blockerUserAlreadyBlockTest() {
		Optional<User> blockerUser = Optional.of(new User());
		blockerUser.get().setUserId(1);
		blockerUser.get().setEmail("jhon@gmail.com");
		
		when(userRepo.findByEmailAndDeletedAt("jhon@gmail.com", null)).thenReturn(blockerUser);
		
		Optional<User> blockedUser = Optional.of(new User());
		blockedUser.get().setUserId(2);
		blockedUser.get().setEmail("tom@gmail.com");
		
		when(userRepo.findByEmailAndDeletedAt("tom@gmail.com", null)).thenReturn(blockedUser);
		
		BlockUser blockUser = new BlockUser();
		blockUser.setBlockedUserId(blockerUser.get());
		
		when(blockUserRepo.findByBlockedUserIdAndDeletedAt(blockerUser.get(), null)).thenReturn(blockUser);
		
		Exception exception = assertThrows(NotFoundException.class, () -> {blockUserService.addBlockUser("jhon@gmail.com", "tom@gmail.com");});
		
		String expectedResult = "Blocker User already blocked";
		
		assertTrue(exception.getMessage().contains(expectedResult));
	}
}
