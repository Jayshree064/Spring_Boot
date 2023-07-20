package com.SpringBootProject.service;

import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringBootProject.config.Encryption;
import com.SpringBootProject.dao.UserRepository;
import com.SpringBootProject.entities.User;
import com.SpringBootProject.exception.NotFoundException;
import com.SpringBootProject.mapper.LoginMapper;
import com.SpringBootProject.mapper.UserMapper;
import com.SpringBootProject.requestDto.UserRequestDto;
import com.SpringBootProject.responseDto.LoginResponseDto;
import com.SpringBootProject.responseDto.ResponseDto;
import com.SpringBootProject.serviceInterface.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private LoginMapper loginMapper;
	
	public List<LoginResponseDto> getAllUsers() {
		List<LoginResponseDto> response = null;
		List<User> usersList =  this.userRepo.findAllAndDeletedAt();
		if(usersList.size()>0) {
			response = userMapper.mapUserListToResponseDto(usersList);
			return response;
		}else {
			throw new NotFoundException("User list is empty.");
		}
	}
	
	public LoginResponseDto getUserById(long userId) {
		Optional<User> user = this.userRepo.findByUserIdAndDeletedAt(userId, null);
		User userData = user.orElseThrow(() -> new NotFoundException("User not found."));
		return loginMapper.mapToLoginResponseDto(userData);
	}
	
	public ResponseDto addNewUser(UserRequestDto userData) throws NoSuchAlgorithmException {
		User user = userMapper.mapUserRequestDtoToUser(userData);
		if(user.getDateOfBirth()!=null)
			user.setDateOfBirth(Date.valueOf(userData.getBirthDate()));
		user.setPassword(Encryption.encryptPassword(userData.getPassword()));
		user.setCreatedAt(new Date(System.currentTimeMillis()));
		userRepo.save(user);
		return new ResponseDto("User added successfully");
	}
	
	public ResponseDto updateUser(long userId,UserRequestDto userData) throws NoSuchAlgorithmException {
		Optional<User> user = userRepo.findByUserIdAndDeletedAt(userId, null);
		User userDetail = user.orElseThrow(() -> new NotFoundException("User not found."));
		Date birthDate = userDetail.getDateOfBirth();
		Date createdAt = userDetail.getCreatedAt();
		userDetail = userMapper.mapUserRequestDtoToUser(userData);
		userDetail.setUserId(userId);
		userDetail.setCreatedAt(createdAt);
		if(!userData.getBirthDate().isEmpty())
			userDetail.setDateOfBirth(Date.valueOf(userData.getBirthDate()));
		else
			userDetail.setDateOfBirth(birthDate);
		userDetail.setUpdatedAt(new Date(System.currentTimeMillis()));
		userDetail.setPassword(Encryption.encryptPassword(userData.getPassword()));
		userRepo.save(userDetail);
		return new ResponseDto("User data updated successfully.");
	}
}
