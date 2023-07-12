package com.SpringBootProject.mapper;

import org.mapstruct.Mapper;

import com.SpringBootProject.entities.User;
import com.SpringBootProject.responseDto.LoginResponseDto;

@Mapper
public interface LoginMapper {
	
	LoginResponseDto mapToLoginResponseDto(User user);
}
