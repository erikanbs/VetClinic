package com.ekholabs.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ekholabs.model.Client;

public interface ClientRepo extends CrudRepository<Client, Integer> {

    List<Client> findAll();

    Client findById(int id);

    List<Client> findByFullNameIgnoreCase(String fullName);

    List<Client> findByEmailIgnoreCase(String email);

}
