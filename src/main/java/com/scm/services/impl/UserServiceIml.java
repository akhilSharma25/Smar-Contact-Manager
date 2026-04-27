package com.scm.services.impl;

import com.scm.model.User;
import com.scm.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceIml implements com.scm.services.UserService {

    @Autowired
    private UserRepo repo;



    @Override
    public User saveUser(User user) {
        return repo.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return repo.findById(id);
    }

    @Override
    public User updateUser(User user) {
//       repo.findById(user.getUserId()).orElseThrow(()->new ResourceNotFoundExpection());
        return null;
    }

    @Override
    public void deleteUser(String id) {

    }

    @Override
    public boolean isUserExist(String userId) {
        return false;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }
}
