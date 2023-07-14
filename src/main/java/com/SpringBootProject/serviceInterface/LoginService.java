package com.SpringBootProject.serviceInterface;

import java.sql.Date;


import com.SpringBootProject.responseDto.LoginResponseDto;

public interface LoginService {

	LoginResponseDto loginUser(String email,String password,Date deletedAt);
}
