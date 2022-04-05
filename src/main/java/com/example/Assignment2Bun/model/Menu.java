package com.example.Assignment2Bun.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int menuId;
    @Column
    private int restaurantIdMenu;
    @Column
    private String menuName;
    @Column
    @ManyToMany( cascade = CascadeType.ALL)
    //@JsonIgnore
    private List<Category> categoryList=new ArrayList<>();
    @OneToOne(mappedBy = "menu")
    //@JsonIgnore
    private Restaurant restaurant;

    public Menu(String menuName, Restaurant restaurant) {
        this.menuName = menuName;
        this.restaurant = restaurant;
    }

    public Menu() {

    }


    public void setRestaurantIdMenu(int restaurantIdMenu) {
        this.restaurantIdMenu = restaurantIdMenu;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void addCategory(Category category){
        categoryList.add(category);
    }

    public void removeCategory(Category category){
        categoryList.remove(category);
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }
    public void printCategories() {
        for (Category category : categoryList) {
            System.out.println(category.getName());
        }
    }

    public int getMenuId() {
        return menuId;
    }

    public int getRestaurantIdMenu() {
        return restaurantIdMenu;
    }

    public String getMenuName() {
        return menuName;
    }

//    @Override
//    public String toString() {
//        return "Menu{" +
//                "menuId=" + menuId +
//                ", restaurantIdMenu=" + restaurantIdMenu +
//                ", menuName='" + menuName + '\'' +
//                ", categoryList=" + categoryList +
//                ", restaurant=" + restaurant +
//                '}';
//    }


}

