package com.groupekilo.security.service;

import java.util.List;
import java.util.Optional;

import com.groupekilo.security.dto.UserDto;

public interface IUserService {
  public List<UserDto>getAll();
  public boolean save(UserDto  userDto);
  public Optional<UserDto>login(String email, String password);
  public boolean update(UserDto  userDto);
  public UserDto get(long id,UserDto  userDto);
  public boolean delete(long id,UserDto userDto);
  //others methods
  public List<UserDto> searchByCriteria(String searchTerm);
  public List<UserDto> filterGetAll(String filterColumn, String filterValue,
		  String sortColumn, String sortOrder);
  public List<UserDto> getPaginatedUsers(int page, int pageSize);
  public int getTotalUserCount();
 
}
