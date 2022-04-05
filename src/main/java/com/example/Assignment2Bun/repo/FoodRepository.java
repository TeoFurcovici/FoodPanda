package com.example.Assignment2Bun.repo;

import com.example.Assignment2Bun.model.Food;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FoodRepository extends CrudRepository<Food,Integer> {
    Optional<Food> findByName(String name);
    Optional<Food> findByCategoryAndRestaurantId(String categoryName, Integer restaurantId);

}
