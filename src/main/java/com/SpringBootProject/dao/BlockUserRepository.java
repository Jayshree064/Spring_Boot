package com.SpringBootProject.dao;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SpringBootProject.entities.BlockUser;
import com.SpringBootProject.entities.User;

public interface BlockUserRepository extends JpaRepository<BlockUser, Long>{

	@Query("from BlockUser where blockerUserId.email =:mail and deletedAt is null")
	public List<BlockUser> findAllBlockUsers(@Param("mail") String mail);
	
	public BlockUser findByBlockedUserIdAndDeletedAt(User user,Date deletedAt);
	
	public Optional<BlockUser> findByBlockUserIdAndDeletedAt(long id,Date deletedAt);
}
