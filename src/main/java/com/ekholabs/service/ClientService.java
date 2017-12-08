package com.ekholabs.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ekholabs.model.Client;
import com.ekholabs.repository.ClientRepo;

@Service
@Transactional
public class ClientService {

	private final ClientRepo clientRepo;

	public ClientService(ClientRepo clientRepo) {
		this.clientRepo = clientRepo;
	}

	public List<Client> findAll() {
		return clientRepo.findAll();
	}

	public Client findById(int id) {
		return clientRepo.findById(id);
	}

	public List<Client> findByFullName(String name) {
		return clientRepo.findByFullNameIgnoreCase(name);
	}

	public List<Client> findByEmail(String email) {
		return clientRepo.findByEmailIgnoreCase(email);
	}

	public Client save(Client client) {
		return clientRepo.save(client);
	}

	public void delete(Client client) {
		clientRepo.delete(client);
	}

}
