package com.SpringBootProject.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SpringBootProject.entities.BlockUser;
import com.SpringBootProject.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	public User findByEmailAndPasswordAndDeletedAt(String email,String password,Date deletedAt);
	
	@Query("from BlockUser where blockerUserId.email =:mail and deletedAt is null")
	public List<BlockUser> findAllBlockUsers(@Param("mail") String mail);
	
}
