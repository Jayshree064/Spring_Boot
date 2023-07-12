package com.SpringBootProject.entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@AllArgsConstructor
@Data
@ToString
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id",length=20,nullable=false)
	private long userId;
	
	@Column(name="first_name",length = 50)
	private String firstName;
	
	@Column(name = "last_name",length = 50)
	private String lastName;
	
	private String email;
	
	private String password;
	
	private String address;
	
	@Column(name = "phone_number",length = 12)
	private long phoneNumber;

	@Column(name = "birth_date")
    private Date dateOfBirth;  
	
	@Column(name="created_at")
	private Date createdAt;
	
	@Column(name="updated_at")
	private Date updatedAt;
	
	@Column(name="deleted_at")
	private Date deletedAt;

}
