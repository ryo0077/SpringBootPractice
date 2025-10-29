package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Admin;
import com.example.demo.repository.AdminRepository;
import com.example.demo.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Transactional
    @Override
    public Admin register(String lastName, String firstName, String email, String rawPassword) {
        if (adminRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("既に登録済みのメールアドレスです。");
        }
        Admin admin = new Admin();
        admin.setLastName(lastName);
        admin.setFirstName(firstName);
        admin.setEmail(email);
        admin.setPassword(BCrypt.hashpw(rawPassword, BCrypt.gensalt()));
        return adminRepository.save(admin);
    }

    @Override
    public Optional<Admin> authenticate(String email, String rawPassword) {
        return adminRepository.findByEmail(email)
                .filter(a -> BCrypt.checkpw(rawPassword, a.getPassword()));
    }

    @Transactional
    @Override
    public void updateSignInTime(Admin admin) {
        admin.setCurrentSignInAt(LocalDateTime.now());
        adminRepository.save(admin);
    }

    @Override
    public boolean existsByEmail(String email) {
        return adminRepository.existsByEmail(email);
    }
}
