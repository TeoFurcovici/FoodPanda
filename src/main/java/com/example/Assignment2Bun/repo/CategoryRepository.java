package com.example.Assignment2Bun.repo;

import com.example.Assignment2Bun.model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface  CategoryRepository extends CrudRepository<Category,Integer> {
    Optional<Category> findByName(String name);
    Optional<Category> findByNameAndRestaurantID(String name, Integer id);
}
