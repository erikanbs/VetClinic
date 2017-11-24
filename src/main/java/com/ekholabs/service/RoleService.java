package com.ekholabs.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ekholabs.model.Role;
import com.ekholabs.repository.RoleRepo;

@Service
@Transactional
public class RoleService {
	
	private final RoleRepo roleRepo;

	public RoleService(RoleRepo roleRepo) {
		this.roleRepo = roleRepo;
	}
	
	public Role findOne(int id) {
		return roleRepo.findById(id);
	}
	
	public void save(Role role) {
		roleRepo.save(role);
	}

}
