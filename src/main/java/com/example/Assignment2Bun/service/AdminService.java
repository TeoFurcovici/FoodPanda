package com.example.Assignment2Bun.service;

import com.example.Assignment2Bun.model.Admin;
import com.example.Assignment2Bun.model.User;
import com.example.Assignment2Bun.repo.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    public  void save(Admin  n) {
        adminRepository.save(n);
    }


    public Optional<Admin> findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }
    @Transactional
    public void deleteByUsername(String username) {
        adminRepository.deleteByUsername(username);
    }
    public Iterable<Admin> findAll() {
        return adminRepository.findAll();
    }
}
