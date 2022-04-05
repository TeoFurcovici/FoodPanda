package com.example.Assignment2Bun.repo;

import com.example.Assignment2Bun.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RestaurantRepository  extends CrudRepository<Restaurant,Integer> {
    Optional<Restaurant> deleteByName(String name);
    Optional<Restaurant> findByName(String name);
    Optional<Restaurant> findById(Integer id);
    Iterable<Restaurant> findByNameContains(String name);
}
