package com.example.Assignment2Bun.repo;

import com.example.Assignment2Bun.model.Menu;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MenuRepository extends CrudRepository<Menu,Integer> {
    Optional<Menu> findByMenuName(String name);
}
