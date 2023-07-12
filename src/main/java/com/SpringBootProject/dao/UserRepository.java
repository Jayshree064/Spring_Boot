package com.SpringBootProject.dao;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SpringBootProject.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	public User findByEmailAndPasswordAndDeletedAt(String email,String password,Date deletedAt);
	
}
