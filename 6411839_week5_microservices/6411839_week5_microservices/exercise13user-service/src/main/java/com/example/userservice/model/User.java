package com.example.userservice.model;

import jakarta.persistence.*;

@Entity
public class User {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String email;
    // getters/setters
}