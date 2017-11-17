package com.ekholabs.repository;

import org.springframework.data.repository.CrudRepository;

import com.ekholabs.model.Role;

public interface RoleRepo extends CrudRepository<Role, String> {
	
	Role findById(Integer id);

}
