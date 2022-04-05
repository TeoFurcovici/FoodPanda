package com.example.Assignment2Bun.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private  int categoryId;
    @Column
    private String name;
    @Column
    private int restaurantID;
    @Column
    //@JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Food> foodList=new ArrayList<>();
    @ManyToMany
    @JsonIgnore
    private List<Menu> menus =new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }

    public Category() {

    }
    public void addMenu(Menu menu){
        menus.add(menu);
    }

    public String getName() {
        return name;
    }

    public List<Food> getFoodList() {
        return foodList;
    }
    public void addFood(Food food){
        foodList.add(food);
    }

    public void removeFood(Food food){
        foodList.remove(food);
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", foodList=" + foodList +
                ", menus=" + menus +
                '}';
    }

    public int getCategoryId() {
        return categoryId;
    }

    public List<Menu> getMenus() {
        return menus;
    }
}