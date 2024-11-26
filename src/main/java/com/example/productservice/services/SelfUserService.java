package com.example.productservice.services;

import com.example.productservice.models.User;
import com.example.productservice.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SelfUserService implements UserService {
    @Autowired
    UserRepo userRepo;

    @Override
    public String register(String email, String name, String password) {
        Optional<User> user = userRepo.findByEmail(email);
        if (user.isPresent()) {
            throw new RuntimeException("User already exists");
        }
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setName(name);
        newUser.setPasswordHash(password);
        userRepo.save(newUser);
        return "User registered successfully";

    }

    @Override
    public String login(String email, String password) {
        Optional<User> userOpt = userRepo.findByEmail(email);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = userOpt.get();
        if (!user.getPasswordHash().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        return "Login successful";
    }

    @Override
    public void resetPassword(String email) {
        Optional<User> userOpt = userRepo.findByEmail(email);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = userOpt.get();
        // send email with reset token
    }

    @Override
    public void updatePassword(String email, String resetToken, String newPassword) {
        Optional<User> userOpt = userRepo.findByEmail(email);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = userOpt.get();
        if (!user.getResetToken().equals(resetToken)) {
            throw new RuntimeException("Invalid reset token");
        }
        user.setPasswordHash(newPassword);
        user.setPasswordHash(null);
        userRepo.save(user);
    }

}
