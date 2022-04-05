package com.example.Assignment2Bun.repo;

import com.example.Assignment2Bun.model.RegularUser;
import com.example.Assignment2Bun.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Integer> {
    Optional<User> findByUsername(String username);
    Optional<User> deleteByUsername(String username);
}

