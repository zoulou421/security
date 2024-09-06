package com.groupekilo.security.service;

import java.util.List;
import java.util.Optional;

import com.groupekilo.security.dto.UserDto;

public interface IUserService {
  public List<UserDto>getAll();
  public boolean save(UserDto  userDto);
  public Optional<UserDto>login(String email, String password);
}
