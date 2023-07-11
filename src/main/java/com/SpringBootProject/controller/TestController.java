package com.SpringBootProject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBootProject.responseDto.ResponseDto;

@RestController
public class TestController {

	private ResponseDto response = new ResponseDto("Hello");
	
	@GetMapping("/test")
	@ResponseBody
	public ResponseEntity<ResponseDto> displayData() {
		try {
			if(response != null)
				return new ResponseEntity<ResponseDto>(response,HttpStatus.OK);
			else
				throw (new Exception());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<ResponseDto>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
