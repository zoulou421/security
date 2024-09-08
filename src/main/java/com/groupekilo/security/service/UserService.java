package com.groupekilo.security.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.groupekilo.security.dao.IUserDao;
import com.groupekilo.security.dao.UserDao;
import com.groupekilo.security.dto.UserDto;
import com.groupekilo.security.entities.UserEntity;
import com.groupekilo.security.mapper.UserMapper;
import com.groupekilo.security.util.ValidationUtil;

public class UserService implements IUserService {
	private IUserDao userDao = new UserDao();

	@Override
	public List<UserDto> getAll() {
		// return userDao.list(new UserEntity());
		return UserMapper.listUserEntityToListUserDto(userDao.list(new UserEntity()));
	}

	@Override
	public boolean save(UserDto userDto) {
		// Validate user data before saving
		if (!validateUser(userDto)) {
			return false; // Validation failed
		}
		return userDao.save(UserMapper.toUserEntity(userDto));
	}

	@Override
	public Optional<UserDto> login(String email, String password) {
		Optional<UserEntity> userEntity = userDao.login(email, password);
		if (userEntity.isPresent()) {
			UserEntity user = userEntity.get();
			return Optional.of(UserMapper.toUserDto(user));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public boolean update(UserDto userDto) {
		// Validate user data before updating
		if (!validateUser(userDto)) {
			return false; // Validation failed
		}
		return userDao.update(UserMapper.toUserEntity(userDto));
	}
	/*
	 * @Override public UserDto get(long id, UserDto userDto) { return
	 * UserMapper.toUserDto(userDao.get(id, UserMapper.toUserEntity(userDto))); }
	 */

	@Override
	public UserDto get(long id, UserDto userDto) {
		UserEntity userEntity = userDao.get(id, new UserEntity());
		return UserMapper.toUserDto(userEntity);
	}

	@Override
	public boolean delete(long id, UserDto userDto) {
		boolean bool = false;
		UserDto userDtoFound = get(id, userDto);
		if (userDtoFound != null) {
			userDao.delete(id, UserMapper.toUserEntity(userDtoFound));
			bool = true;
		}
		return bool;
	}

	public List<UserDto> searchByCriteria(String searchTerm) {
		List<UserEntity> userEntities = userDao.searchByCriteria(searchTerm);
		return UserMapper.listUserEntityToListUserDto(userEntities);
	}

	// Private method for user validation
	private boolean validateUser(UserDto userDto) {
		if (!ValidationUtil.isNotEmpty(userDto.getFirstName())) {
			return false; // First name is required
		}
		if (!ValidationUtil.isNotEmpty(userDto.getLastName())) {
			return false; // Last name is required
		}
		if (!ValidationUtil.isValidEmail(userDto.getEmail())) {
			return false; // Invalid email format
		}
		if (!ValidationUtil.isValidPassword(userDto.getPassword())) {
			return false; // Password must be at least 8 characters
		}
		return true; // Validation passed
	}

	@Override
	public List<UserDto> filterGetAll(String filterColumn, String filterValue, String sortColumn, String sortOrder) {
		return UserMapper.listUserEntityToListUserDto(
                userDao.myListFilter(new UserEntity(), filterColumn, filterValue, sortColumn, sortOrder));
		
	}

	@Override
	public List<UserDto> getPaginatedUsers(int page, int pageSize) {
		List<UserEntity> entities = userDao.getPaginatedUsers(page, pageSize);
        return entities.stream().map(UserMapper::toUserDto).collect(Collectors.toList());
	}

	@Override
	public int getTotalUserCount() {
		  return userDao.countAllUsers();
	}
	
	

}
