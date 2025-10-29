package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.Admin;

public interface AdminService {

    Admin register(String lastName, String firstName, String email, String rawPassword);

    Optional<Admin> authenticate(String email, String rawPassword);

    void updateSignInTime(Admin admin);

    boolean existsByEmail(String email);
}

