package com.SpringBootProject.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.SpringBootProject.entities.BlockUser;
import com.SpringBootProject.responseDto.BlockUserResponseDto;

@Mapper
public interface BlockUserMapper {

	List<BlockUserResponseDto> mapToResponse(List<BlockUser> blockUser);
	
	BlockUserResponseDto mapBlockUserToResponseDto(BlockUser blockuser);
}
