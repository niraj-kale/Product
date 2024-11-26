package com.example.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseModel {
    String email;
    String name;
    String passwordHash;
    String resetToken;
    LocalDateTime resetTokenExpiry;
}
