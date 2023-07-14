package com.SpringBootProject.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.SpringBootProject.config.Encryption;
import com.SpringBootProject.dao.UserRepository;
import com.SpringBootProject.entities.User;
import com.SpringBootProject.exception.NotFoundException;
import com.SpringBootProject.mapper.LoginMapper;
import com.SpringBootProject.responseDto.LoginResponseDto;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness =  Strictness.LENIENT)
public class LoginUserServiceTest {

	@Mock
	private UserRepository userRepo;
	
	@InjectMocks
	private LoginServiceImpl loginService;
	
	@Mock
	private LoginMapper mapper;
	
	@Test
	public void userLoginTest() throws NoSuchAlgorithmException {
		
		String email = "test@gmail.com";
		String password = Encryption.encryptPassword("123456");
		
		Optional<User> user = Optional.of(new User());
		user.get().setEmail(email);
		user.get().setPassword(password);
		
		User u = new User();
		u.setEmail(email);
		u.setPassword(password);
		
		when(userRepo.findByEmailAndPasswordAndDeletedAt(email, password, null)).thenReturn(user);
		
		LoginResponseDto expectedResult = new LoginResponseDto();
		expectedResult.setEmail(email);
		expectedResult.setPassword(password);
		
		when(mapper.mapToLoginResponseDto(u)).thenReturn(expectedResult);
		
		LoginResponseDto actualResult = loginService.loginUser(email,password, null);
		assertThat(actualResult.equals(expectedResult));
		
	}
	
	@Test
	public void userNotFoundTest() throws NoSuchAlgorithmException {
		
		String email = "test@gmail.com";
		String password = Encryption.encryptPassword("123456");
		
		Optional<User> user = Optional.of(new User());
		user.get().setEmail(email);
		user.get().setPassword(password);
		
		when(userRepo.findByEmailAndPasswordAndDeletedAt(email, password, null)).thenReturn(user);
		
		Exception exception = assertThrows(NotFoundException.class, () -> {loginService.loginUser("abc@gamil.com",password, null);});
		
		String expectedMessage = "User not found";
		
		assertTrue(exception.getMessage().contains(expectedMessage));
	}

}
