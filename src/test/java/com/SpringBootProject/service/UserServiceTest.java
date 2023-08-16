package com.SpringBootProject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.SpringBootProject.config.Encryption;
import com.SpringBootProject.dao.UserRepository;
import com.SpringBootProject.entities.User;
import com.SpringBootProject.exception.NotFoundException;
import com.SpringBootProject.mapper.LoginMapper;
import com.SpringBootProject.mapper.UserMapper;
import com.SpringBootProject.requestDto.UserRequestDto;
import com.SpringBootProject.responseDto.LoginResponseDto;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@Mock
	private UserRepository userRepo;
	
	@InjectMocks
	private UserServiceImpl userService;
	
	@Mock
	private LoginMapper responseMapper;
	
	@Mock
	private UserMapper userMapper;
	
	@Test
	public void getAllUsersTest() {
		User user = new User();
		user.setEmail("jhon@gmail.com");
		user.setFirstName("Jhon");
		
		List<User> userList = new ArrayList<>();
		userList.add(user);
		
		when(userRepo.findAllAndDeletedAt()).thenReturn(userList);
		
		LoginResponseDto response = new LoginResponseDto();
		response.setEmail("jhon@gmail.com");
		response.setFirstName("Jhon");
		
		List<LoginResponseDto> expectedResult = new ArrayList<>();
		expectedResult.add(response);
		
		when(userMapper.mapUserListToResponseDto(userList)).thenReturn(expectedResult);
		
		assertEquals(userService.getAllUsers(),expectedResult);
	}
	
	@Test
	public void emptyUserListTest() {
		List<User> userList = new ArrayList<>();
		
		when(userRepo.findAllAndDeletedAt()).thenReturn(userList);
		
		Exception exception = assertThrows(NotFoundException.class, () -> {userService.getAllUsers();});
		
		String expectedMessage = "User list is empty.";
		
		assertTrue(exception.getMessage().contains(expectedMessage));
	}
	
	@Test
	public void getUserByIdTest() {
		Optional<User> user = Optional.of(new User());
		user.get().setUserId(1L);
		user.get().setEmail("jhon@gmail.com");
		user.get().setFirstName("Jhon");
		
		when(userRepo.findByUserIdAndDeletedAt(1L,null)).thenReturn(user);
		
		LoginResponseDto expectedResult = new LoginResponseDto();
		expectedResult.setUserId(1L);
		expectedResult.setEmail("jhon@gmail.com");
		expectedResult.setFirstName("Jhon");
		
		when(responseMapper.mapToLoginResponseDto(user.get())).thenReturn(expectedResult);
		
		assertEquals(userService.getUserById(1L), expectedResult);
	}
	
	@Test
	public void userNotFoundTest() {
		Optional<User> user = Optional.empty();
		
		when(userRepo.findByUserIdAndDeletedAt(1L, null)).thenReturn(user);
		
		Exception exception = assertThrows(NotFoundException.class, () -> {userService.getUserById(1L);});
		
		String expectedMessage = "User not found.";
		
		assertTrue(exception.getMessage().contains(expectedMessage));
	}
	
	@Test
	public void addNewUserTest() throws NoSuchAlgorithmException {
		UserRequestDto userData = new UserRequestDto();
		userData.setEmail("jhon@gmail.com");
		userData.setFirstName("Jhon");
		userData.setLastName("Doe");
		userData.setPassword("1234");
		
		User user = new User();
		user.setEmail("jhon@gmail.com");
		user.setFirstName("Jhon");
		user.setLastName("Doe");
		user.setPassword("1234");
		
		when(userMapper.mapUserRequestDtoToUser(userData)).thenReturn(user);
		
		user.setPassword(Encryption.encryptPassword(user.getPassword()));
		user.setCreatedAt(new Date(System.currentTimeMillis()));
		
		when(userRepo.save(user)).thenReturn(user);
		
		String expectedMessage = "User added successfully";
		
		assertTrue(userService.addNewUser(userData).getMessage().equals(expectedMessage));
		
	}
	
	@Test
	public void userDataWithBirthDateTest() throws NoSuchAlgorithmException {
		UserRequestDto userData = new UserRequestDto();
		userData.setEmail("jhon@gmail.com");
		userData.setFirstName("Jhon");
		userData.setLastName("Doe");
		userData.setPassword("1234");
		userData.setBirthDate("2001-06-12");
		
		User user = new User();
		user.setEmail("jhon@gmail.com");
		user.setFirstName("Jhon");
		user.setLastName("Doe");
		user.setPassword("1234");
		
		when(userMapper.mapUserRequestDtoToUser(userData)).thenReturn(user);
		
		user.setDateOfBirth(Date.valueOf(userData.getBirthDate()) );
		user.setPassword(Encryption.encryptPassword(user.getPassword()));
		user.setCreatedAt(new Date(System.currentTimeMillis()));
		
		when(userRepo.save(user)).thenReturn(user);
		
		String expectedMessage = "User added successfully";
		
		assertTrue(userService.addNewUser(userData).getMessage().equals(expectedMessage));
	}
	
	@Test
	public void updateUserTest() throws NoSuchAlgorithmException {
		Optional<User> userData = Optional.of(new User());
		userData.get().setUserId(1);
		userData.get().setEmail("jhon@gmail.com");
		userData.get().setFirstName("Jhon");
		userData.get().setPassword("1234");
		userData.get().setCreatedAt(new Date(System.currentTimeMillis()));
		
		when(userRepo.findByUserIdAndDeletedAt(1, null)).thenReturn(userData);
		
		UserRequestDto userRequestData = new UserRequestDto();
		userRequestData.setEmail("jhon@gmail.com");
		userRequestData.setFirstName("Jhon");
		userRequestData.setLastName("Doe");
		userRequestData.setPassword("1234");
		userRequestData.setBirthDate("2002-12-12");
		
		when(userMapper.mapUserRequestDtoToUser(userRequestData)).thenReturn(userData.get());
		
		userData.get().setDateOfBirth(Date.valueOf(userRequestData.getBirthDate()));
		userData.get().setCreatedAt(userData.get().getCreatedAt());
		userData.get().setPassword(Encryption.encryptPassword(userRequestData.getPassword()));
		userData.get().setUpdatedAt(new Date(System.currentTimeMillis()));
		
		when(userRepo.save(userData.get())).thenReturn(userData.get());
		
		String expectedResult = "User data updated successfully.";
		
		assertTrue(userService.updateUser(1L, userRequestData).getMessage().equals(expectedResult));
	}
	
	@Test
	public void userNotFoundInUpdateTest() {
		Optional<User> user = Optional.empty();
		
		when(userRepo.findByUserIdAndDeletedAt(1L, null)).thenReturn(user);
		
		Exception exception = assertThrows(NotFoundException.class, () -> {userService.updateUser(1l, null);});
		
		String expectedMessage = "User not found.";
		
		assertTrue(exception.getMessage().contains(expectedMessage));
	}
	
	@Test
	public void birthDateNotUpdateTest() throws NoSuchAlgorithmException {
		Optional<User> userData = Optional.of(new User());
		userData.get().setUserId(1);
		userData.get().setEmail("jhon@gmail.com");
		userData.get().setFirstName("Jhon");
		userData.get().setPassword("1234");
		userData.get().setDateOfBirth(Date.valueOf("2001-12-12"));
		userData.get().setCreatedAt(new Date(System.currentTimeMillis()));
		
		when(userRepo.findByUserIdAndDeletedAt(1, null)).thenReturn(userData);
		
		UserRequestDto userRequestData = new UserRequestDto();
		userRequestData.setEmail("jhon@gmail.com");
		userRequestData.setFirstName("Jhon");
		userRequestData.setLastName("Doe");
		userRequestData.setPassword("1234");
		userRequestData.setBirthDate("");
		
		when(userMapper.mapUserRequestDtoToUser(userRequestData)).thenReturn(userData.get());
		
		userData.get().setCreatedAt(userData.get().getCreatedAt());
		userData.get().setPassword(Encryption.encryptPassword(userRequestData.getPassword()));
		userData.get().setUpdatedAt(new Date(System.currentTimeMillis()));
		
		when(userRepo.save(userData.get())).thenReturn(userData.get());
		
		String expectedResult = "User data updated successfully.";
		System.out.println("Result "+userService.updateUser(1L, userRequestData).getMessage());
		assertTrue(userService.updateUser(1L, userRequestData).getMessage().equals(expectedResult));
	}
}
