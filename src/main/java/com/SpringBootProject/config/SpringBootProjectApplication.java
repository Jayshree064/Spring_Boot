package com.SpringBootProject.config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan(basePackages = "com.SpringBootProject")
@EntityScan(basePackages = "com.SpringBootProject.entities")
@EnableJpaRepositories(basePackages = "com.SpringBootProject.dao")
public class SpringBootProjectApplication{

	public static void main(String[] args) {
		SpringApplication.run(SpringBootProjectApplication.class, args);
	}
	
}
