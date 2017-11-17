package com.ekholabs.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ekholabs.model.User;

public interface UserRepo extends CrudRepository<User, Integer> {
	
	List<User> findAll();

}
