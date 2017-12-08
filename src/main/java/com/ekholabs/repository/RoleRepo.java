package com.ekholabs.repository;

import org.springframework.data.repository.CrudRepository;

import com.ekholabs.model.Role;

public interface RoleRepo extends CrudRepository<Role, Integer> {

	Role findById(Integer id);

}
