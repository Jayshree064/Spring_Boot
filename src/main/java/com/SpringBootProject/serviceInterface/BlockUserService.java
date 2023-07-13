package com.SpringBootProject.serviceInterface;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.SpringBootProject.responseDto.BlockUserResponseDto;

public interface BlockUserService {
	
	ResponseEntity<List<BlockUserResponseDto>> blockUserData(String email);
}
