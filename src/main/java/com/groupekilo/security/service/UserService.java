package com.groupekilo.security.service;

import java.util.List;
import java.util.Optional;

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
	@Override
	public boolean save(UserDto userDto) {
		return userDao.save(UserMapper.toUserEntity(userDto));
	}
	@Override
	public Optional<UserDto> login(String email, String password) {
		Optional<UserEntity>userEntity=userDao.login(email, password);
		if(userEntity.isPresent()) {
			UserEntity user=userEntity.get();
			return Optional.of(UserMapper.toUserDto(user));
		}else {
			return Optional.empty();
		}
	}
	@Override
	public boolean update(UserDto userDto) {
		return userDao.update(UserMapper.toUserEntity(userDto));
	}
	/*@Override
	public UserDto get(long id, UserDto userDto) {
		return UserMapper.toUserDto(userDao.get(id, UserMapper.toUserEntity(userDto)));
	}*/
	
	@Override
	public UserDto get(long id, UserDto userDto) {
	    UserEntity userEntity = userDao.get(id, new UserEntity());
	    return UserMapper.toUserDto(userEntity);
	}
	@Override
	public boolean delete(long id, UserDto userDto) {
		boolean bool=false;
		UserDto userDtoFound=get(id, userDto);
		if(userDtoFound!=null) {
			userDao.delete(id, UserMapper.toUserEntity(userDtoFound));
			bool=true;
		}
		return bool;
	}
	public List<UserDto> searchByCriteria(String searchTerm) {
        List<UserEntity> userEntities = userDao.searchByCriteria(searchTerm);
        return UserMapper.listUserEntityToListUserDto(userEntities);
    }

	
	
	

}
