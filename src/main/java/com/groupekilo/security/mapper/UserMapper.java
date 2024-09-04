package com.groupekilo.security.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.groupekilo.security.dto.UserDto;
import com.groupekilo.security.entities.UserEntity;

public class UserMapper {

	public List<UserDto> listUserEntityToListUserDto(List<UserEntity> users) {
		return users.stream()
				.map(user->this.toUserDto(user)).collect(Collectors.toList());
	}

	private UserDto toUserDto(UserEntity user) {
		
		return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());

	}

}
