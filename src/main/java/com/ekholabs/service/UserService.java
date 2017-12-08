package com.ekholabs.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ekholabs.model.User;
import com.ekholabs.repository.UserRepo;

@Service
@Transactional
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public User findOne(int id) {
        return userRepo.findOne(id);
    }

    public List<User> findByFullName(String fullName) {
        return userRepo.findAllByFullNameIgnoreCase(fullName);
    }

    public void create(User user) {
        userRepo.save(user);
    }

    public User save(User user) {
        return userRepo.save(user);
    }

    public void delete(User user) {
        userRepo.delete(user);
    }

}
