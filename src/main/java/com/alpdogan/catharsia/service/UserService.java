package com.alpdogan.catharsia.service;

import com.alpdogan.catharsia.entity.User;
import com.alpdogan.catharsia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findUserById(id);
    }

    public void updateUserByEmail(String email, User user) {
        user.setBio(user.getBio());
        user.setEmail(email);
        userRepository.save(user);
    }

    public void deleteUserById(int id) {
        userRepository.delete(userRepository.findUserById(id));
    }

}
