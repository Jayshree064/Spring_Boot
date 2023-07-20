package com.SpringBootProject.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.SpringBootProject.entities.User;
import com.SpringBootProject.requestDto.UserRequestDto;
import com.SpringBootProject.responseDto.LoginResponseDto;

@Mapper
public interface UserMapper {

	List<LoginResponseDto> mapUserListToResponseDto(List<User> user);
	
	User mapUserRequestDtoToUser(UserRequestDto user);
}
