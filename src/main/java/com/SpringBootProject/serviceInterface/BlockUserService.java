package com.SpringBootProject.serviceInterface;

import java.util.List;


import com.SpringBootProject.responseDto.BlockUserResponseDto;

public interface BlockUserService {
	
	List<BlockUserResponseDto> blockUserData(String email);
}
