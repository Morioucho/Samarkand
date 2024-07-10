package com.morioucho.samarkand.service;

import com.morioucho.samarkand.model.User;
import com.morioucho.samarkand.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public void save(User user){
        userRepository.save(user);
    }
}
