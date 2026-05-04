package com.scm.services.impl;

import com.scm.helper.AppConstants;
import com.scm.helper.ResourceNotFoundExpection;
import com.scm.model.User;
import com.scm.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceIml implements com.scm.services.UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public User saveUser(User user) {
        //userid
        String userId= UUID.randomUUID().toString();
        user.setUserId(userId);

        user.setPassword(passwordEncoder.encode(user.getPassword()));


        user.setRoleList(List.of(AppConstants.ROLE_USER));
        return repo.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return repo.findById(id);
    }

    @Override
    public User updateUser(User user) {
      User user1= repo.findById(user.getUserId()).orElseThrow(()->new ResourceNotFoundExpection("User not found"));
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setPassword(user1.getPassword());
        user1.setAbout(user.getAbout());
        user1.setPhoneNumber(user.getPhoneNumber());
        user1.setProfilePic(user.getProfilePic());
        user.setEnabled(user.getEnabled());
        user1.setEmailVerified(user.getEmailVerified());
        user1.setPhoneVerified(user.getPhoneVerified());
        user1.setProviderUserId(user.getProviderUserId());
        return repo.save(user1);
    }

    @Override
    public void deleteUser(String id) {
        com.scm.model.User user =repo.findById(id).orElseThrow(()->new ResourceNotFoundExpection("User not found"));
     repo.delete(user);
    }

    @Override
    public boolean isUserExist(String userId) {
        User user1= repo.findById(userId).orElse(null);
        return user1!=null?true:false;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        User user1= repo.findByEmail(email).orElse(null);

        return user1!=null?true:false;
    }

    @Override
    public List<User> getAllUsers() {
        return repo.findAll();
    }
}
