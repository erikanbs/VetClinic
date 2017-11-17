package com.ekholabs.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ekholabs.model.Client;
import com.ekholabs.model.Pet;

public interface PetRepo extends CrudRepository<Pet, Integer> {
	
	List<Pet> findAll();
	
	Pet findById(int id);
	
	List<Pet> findByNameIgnoreCase(String name);
	
	List<Pet> findByOwner(Client owner);

}
