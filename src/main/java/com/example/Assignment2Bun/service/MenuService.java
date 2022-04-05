package com.example.Assignment2Bun.service;

import com.example.Assignment2Bun.model.Menu;
import com.example.Assignment2Bun.repo.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;
    public  void save(Menu n) {
        menuRepository.save(n);
    }
    public Optional<Menu> findByMenuName(String name)
    {
        return  menuRepository.findByMenuName(name);
    }

}
