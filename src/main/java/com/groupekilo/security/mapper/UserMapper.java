package com.groupekilo.security.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.groupekilo.security.dto.UserDto;
import com.groupekilo.security.entities.UserEntity;

public class UserMapper {

	// mapper list
	public static List<UserDto> listUserEntityToListUserDto(List<UserEntity> users) {
		return users.stream().map(user -> toUserDto(user)).collect(Collectors.toList());
	}

	public static UserDto toUserDto(UserEntity user) {

		return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
	}

	public static UserEntity toUserEntity(UserDto user) {

		return new UserEntity(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(),
				user.getPassword());
	}

}
