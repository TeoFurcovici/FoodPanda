package com.example.Assignment2Bun.service;

import com.example.Assignment2Bun.model.Food;
import com.example.Assignment2Bun.repo.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FoodService {
    @Autowired
    private FoodRepository foodRepository;
    public  void save(Food n) {
        foodRepository.save(n);
    }
    public Optional<Food> findByName(String name)
    {
        return  foodRepository.findByName(name);
    }
    public Optional<Food> findByCategoryAndRestaurantId (String catName, Integer restId)
    {
        return  foodRepository.findByCategoryAndRestaurantId(catName,restId);
    }


}
