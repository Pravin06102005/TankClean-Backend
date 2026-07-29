package com.tankclean.TankClean.service;


import com.tankclean.TankClean.dto.RegisterRequest;
import com.tankclean.TankClean.entity.Users;
import com.tankclean.TankClean.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired // “Spring, please give me the object of this class automatically. I don’t want to create it using new.”
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Users register(RegisterRequest registerRequest) {
        Users user = new Users();
        user.setName(registerRequest.getName());
        user.setEmail(registerRequest.getEmail());
        user.setPhone(registerRequest.getPhone());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        user.setRole("CUSTOMER");
        return userRepository.save(user);
    }

    public Users registerAdmin(RegisterRequest registerRequest) {
        if (userRepository.existsByRole("ADMIN")) {
            throw new RuntimeException("Only one admin is allowed");
        }

        Users admin = new Users();
        admin.setName(registerRequest.getName());
        admin.setEmail(registerRequest.getEmail());
        admin.setPhone(registerRequest.getPhone());
        admin.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        admin.setCreatedAt(LocalDateTime.now());
        admin.setRole("ADMIN");
        return userRepository.save(admin);
    }

    public List<Users> findAll() {
        return userRepository.findAll();
    }
    public Optional<Users> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Users findByID(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public Users Update(Long id,Users updateuser) {

        Users user = userRepository.findById(id).orElseThrow();
        user.setName(updateuser.getName());
        user.setEmail(updateuser.getEmail());
        user.setPhone(updateuser.getPhone());
        user.setPassword(passwordEncoder.encode(updateuser.getPassword()));
        return userRepository.save(user);
    }

    public Users updateCurrentUser(String email, Users updateUser) {
        Users user = userRepository.findByEmail(email).orElseThrow();
        user.setName(updateUser.getName());
        user.setPhone(updateUser.getPhone());
        if (updateUser.getPassword() != null && !updateUser.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(updateUser.getPassword()));
        }
        return userRepository.save(user);
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }
}
