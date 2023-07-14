package com.SpringBootProject.config;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.SpringBootProject.dao.UserRepository;
import com.SpringBootProject.entities.User;

@SpringBootApplication
@ComponentScan(basePackages = "com.SpringBootProject")
@EntityScan(basePackages = "com.SpringBootProject.entities")
@EnableJpaRepositories(basePackages = "com.SpringBootProject.dao")
public class SpringBootProjectApplication implements CommandLineRunner{

//	@Autowired
//	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
//		User user = new User();
//		user.setFirstName("Jhon");
//		user.setLastName("Doe");
//		user.setEmail("jhondoe@gmail.com");
//		user.setAddress("B-202,Apexa Apartment");
//		String bdate = "2002-05-06";
//		user.setDateOfBirth(Date.valueOf(bdate));
//		user.setPhoneNumber(Long.parseLong("9865321247"));
//		user.setCreatedAt(new Date(System.currentTimeMillis()));
//		user.setPassword(Encryption.encryptPassword("123456"));
//		this.userRepository.save(user);
//		
//		User user1 = new User();
//		user1.setFirstName("Jessica");
//		user1.setLastName("Smith");
//		user1.setEmail("jessica@gmail.com");
//		user1.setAddress("B-202,Apexa Apartment");
//		String bdate1 = "2002-05-06";
//		user1.setDateOfBirth(Date.valueOf(bdate1));
//		user1.setPhoneNumber(Long.parseLong("9865321247"));
//		user1.setCreatedAt(new Date(System.currentTimeMillis()));
//		user1.setDeletedAt(new Date(System.currentTimeMillis()));
//		user1.setPassword(Encryption.encryptPassword("123456"));
//		this.userRepository.save(user1);
	}
	

}
