package com.SpringBootProject.responseDto;

import java.sql.Date;

import com.SpringBootProject.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlockUserResponseDto {
	
	private long blockUserId;
	private User blockerUserId;
	private User blockedUserId;
	private Date createdAt;
	private Date updatedAt;
	private Date deletedAt;
}
