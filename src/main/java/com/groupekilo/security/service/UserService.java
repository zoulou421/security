package com.groupekilo.security.service;

import java.util.List;

import com.groupekilo.security.dao.IUserDao;
import com.groupekilo.security.dao.UserDao;
import com.groupekilo.security.dto.UserDto;
import com.groupekilo.security.entities.UserEntity;

public class UserService implements IUserService {
private IUserDao userDao=new UserDao();
	@Override
	public List<UserDto> getAll() {
		// TODO Auto-generated method stub
		return userDao.list(new UserEntity());
	}

}
