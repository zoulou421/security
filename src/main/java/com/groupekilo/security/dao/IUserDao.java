package com.groupekilo.security.dao;

import java.util.List;
import java.util.Optional;

import com.groupekilo.security.entities.UserEntity;

public interface IUserDao extends Repository<UserEntity> {
 public Optional<UserEntity>login(String email, String password);
 public List<UserEntity> searchByCriteria(String searchTerm);
 
 public List<UserEntity> myListFilter(UserEntity entity, String filterColumn, String filterValue, String sortColumn, String sortOrder);


 public List<UserEntity> getPaginatedUsers(int page, int pageSize);
 public int countAllUsers(); // To get total number of users for pagination
 
}
