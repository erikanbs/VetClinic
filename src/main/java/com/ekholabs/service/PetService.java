package com.ekholabs.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ekholabs.model.Pet;
import com.ekholabs.repository.PetRepo;

@Service
@Transactional
public class PetService {

	private final PetRepo petRepo;

	public PetService(PetRepo petRepo) {
		this.petRepo = petRepo;
	}

	public List<Pet> findAll() {
		return petRepo.findAll();
	}

	public Pet findById(int id) {
		return petRepo.findById(id);
	}

	public Pet save(Pet pet) {
		return petRepo.save(pet);
	}

	public void delete(Pet pet) {
		petRepo.delete(pet);
	}

}
