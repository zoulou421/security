package com.groupekilo.security.dao;

import java.util.Optional;

import com.groupekilo.security.entities.UserEntity;

public interface IUserDao extends Repository<UserEntity> {
 public Optional<UserEntity>login(String email, String password);
}
