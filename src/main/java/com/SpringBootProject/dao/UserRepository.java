package com.SpringBootProject.dao;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.SpringBootProject.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	public Optional<User> findByEmailAndPasswordAndDeletedAt(String email,String password,Date deletedAt);
	
	public Optional<User> findByEmailAndDeletedAt(String email,Date deletedAt);
	
	@Query("from User where deletedAt is NULL")
	public List<User> findAllAndDeletedAt();
	
	public Optional<User> findByUserIdAndDeletedAt(long userId,Date deletedAt);
	
}
