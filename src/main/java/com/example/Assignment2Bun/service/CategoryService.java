package com.example.Assignment2Bun.service;

import com.example.Assignment2Bun.model.Category;
import com.example.Assignment2Bun.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public  void save(Category n) {
        categoryRepository.save(n);
    }
    public Optional<Category> findByName(String name)
    {
        return categoryRepository.findByName(name);
    }
    public Optional<Category> findByNameAndRestaurantID(String name, Integer id)
    {
        return categoryRepository.findByNameAndRestaurantID(name,id);
    }
}
