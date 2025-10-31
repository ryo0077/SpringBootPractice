package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Admin;
import com.example.demo.repository.AdminRepository;
import com.example.demo.service.AdminService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
	
	private final AdminRepository adminRepository;
	private final PasswordEncoder passwordEncoder;

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
        admin.setPassword(passwordEncoder.encode(rawPassword));
        return adminRepository.save(admin);
    }

    @Override
    public Optional<Admin> authenticate(String email, String rawPassword) {
        return adminRepository.findByEmail(email)
            .filter(a -> passwordEncoder.matches(rawPassword, a.getPassword()));
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
