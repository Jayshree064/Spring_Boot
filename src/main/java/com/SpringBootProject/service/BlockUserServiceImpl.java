package com.SpringBootProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.SpringBootProject.dao.UserRepository;
import com.SpringBootProject.entities.BlockUser;
import com.SpringBootProject.exception.NotFoundException;
import com.SpringBootProject.mapper.BlockUserMapper;
import com.SpringBootProject.responseDto.BlockUserResponseDto;
import com.SpringBootProject.serviceInterface.BlockUserService;

@Service
public class BlockUserServiceImpl implements BlockUserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlockUserMapper mapper;
	
	public ResponseEntity<List<BlockUserResponseDto>> blockUserData(String email){
		List<BlockUserResponseDto> response = null;
		List<BlockUser> userList = this.userRepository.findAllBlockUsers(email);
		if(userList.size() > 0) {
			response = mapper.mapToResponse(userList);
			return new ResponseEntity<List<BlockUserResponseDto>>(response,HttpStatus.OK);
		}else {
			throw new NotFoundException("User block list not found");
		}
	}
}
