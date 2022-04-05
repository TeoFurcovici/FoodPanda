package com.example.Assignment2Bun.repo;

import com.example.Assignment2Bun.model.Admin;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AdminRepository extends CrudRepository<Admin,Integer> {
    Optional<Admin> findByUsername(String username);
    void deleteByUsername(String username);
}
