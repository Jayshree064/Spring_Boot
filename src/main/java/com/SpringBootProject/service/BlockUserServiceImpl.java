package com.SpringBootProject.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringBootProject.dao.BlockUserRepository;
import com.SpringBootProject.dao.UserRepository;
import com.SpringBootProject.entities.BlockUser;
import com.SpringBootProject.entities.User;
import com.SpringBootProject.exception.NotFoundException;
import com.SpringBootProject.mapper.BlockUserMapper;
import com.SpringBootProject.responseDto.BlockUserResponseDto;
import com.SpringBootProject.serviceInterface.BlockUserService;

@Service
public class BlockUserServiceImpl implements BlockUserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlockUserRepository blockUserRepo;

	@Autowired
	private BlockUserMapper mapper;
	
	public List<BlockUserResponseDto> blockUserData(String email){
		List<BlockUserResponseDto> response = null;
		List<BlockUser> userList = this.blockUserRepo.findAllBlockUsers(email);
		if(userList.size() > 0) {
			response = mapper.mapToResponse(userList);
			return response;
		}else {
			throw new NotFoundException("User block list not found");
		}
	}
	
	public BlockUserResponseDto addBlockUser(String blockerEmail,String blockedEmail) {
		Optional<User> blockerUser = userRepository.findByEmailAndDeletedAt(blockerEmail, null);
		User blockerUserData = blockerUser.orElseThrow(() -> new NotFoundException("Blocker User not found"));
		Optional<User> blockedUser = userRepository.findByEmailAndDeletedAt(blockedEmail, null);
		User blockedUserData = blockedUser.orElseThrow(() -> new NotFoundException("Blocked User not found"));
		
		BlockUser isUserBlocked = blockUserRepo.findByBlockedUserIdAndDeletedAt(blockedUserData, null);
		BlockUser isBlockerUserBlocked = blockUserRepo.findByBlockedUserIdAndDeletedAt(blockerUserData, null);
		
		if(isBlockerUserBlocked == null) {
			if(isUserBlocked == null) {
				BlockUser blockUserData = new BlockUser();
				blockUserData.setBlockerUserId(blockerUserData);
				blockUserData.setBlockedUserId(blockedUserData);
				blockUserData.setCreatedAt(new Date(System.currentTimeMillis()));
				blockUserRepo.save(blockUserData);
				return mapper.mapBlockUserToResponseDto(blockUserData);
			}else {
				throw new NotFoundException("User already blocked");
			}
		}else {
			throw new NotFoundException("Blocker User already blocked");
		}
	}
	
	public BlockUserResponseDto removeBlockUser(long id) {
		Optional<BlockUser> removedUser = blockUserRepo.findByBlockUserIdAndDeletedAt(id,null);
		BlockUser removeUserData = removedUser.orElseThrow(()-> new NotFoundException("User not found"));
		removeUserData.setDeletedAt(new Date(System.currentTimeMillis()));
		blockUserRepo.save(removeUserData);
		return mapper.mapBlockUserToResponseDto(removeUserData);
	}
	
}
