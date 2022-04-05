package com.example.Assignment2Bun.service;

import com.example.Assignment2Bun.model.Restaurant;
import com.example.Assignment2Bun.model.User;
import com.example.Assignment2Bun.repo.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;
    public  void save(Restaurant n) {
        restaurantRepository.save(n);
    }
    @Transactional
    public void deleteByName(String name) {
        restaurantRepository.deleteByName(name);
    }
    public Optional<Restaurant> findByName(String name)
    {
         return  restaurantRepository.findByName(name);
    }
    public Iterable<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }
    public Optional<Restaurant> findById(Integer id) {
        return restaurantRepository.findById(id);
    }
    public Iterable<Restaurant> findByNameContains(String name) {
        return restaurantRepository.findByNameContains(name);
    }
}
