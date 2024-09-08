package com.groupekilo.security.application;

import com.groupekilo.security.dto.UserDto;
import com.groupekilo.security.mapper.UserMapper;
import com.groupekilo.security.service.IUserService;
import com.groupekilo.security.service.UserService;

public class TestApp {

	public static void main(String[] args) {
		UserDto userDto=new UserDto();
		userDto.setFirstName("ftest1");
		userDto.setLastName("ltest1");
		userDto.setEmail("lf@gmail.com");
		userDto.setPassword("123");
		
		System.out.println(userDto.getId()+" "+userDto.getFirstName()
		+userDto.getLastName()+" "+userDto.getEmail()+" "+userDto.getPassword());
		
		
		
		

	}

}
