package com.groupekilo.security.service;

import java.util.List;

import com.groupekilo.security.dao.IUserDao;
import com.groupekilo.security.dao.UserDao;
import com.groupekilo.security.dto.UserDto;
import com.groupekilo.security.entities.UserEntity;
import com.groupekilo.security.mapper.UserMapper;

public class UserService implements IUserService {
private IUserDao userDao=new UserDao();
	@Override
	public List<UserDto> getAll() {
		//return userDao.list(new UserEntity());
		return UserMapper.listUserEntityToListUserDto(userDao.list(new UserEntity()));
	}

}
