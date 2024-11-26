package com.example.productservice.services;

public interface UserService {
    String register(String email, String name, String password);
    String login(String email, String password);
    void resetPassword(String email);
    void updatePassword(String email, String resetToken, String newPassword);
}
