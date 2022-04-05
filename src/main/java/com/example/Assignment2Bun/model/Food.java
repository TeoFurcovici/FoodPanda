package com.example.Assignment2Bun.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Food implements BaseComponent{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int idFood;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private Double price;
    @ManyToOne
    private Restaurant restaurantId;
    @ManyToOne
    @JsonIgnore
    private Category category;
    @ManyToMany
    @JsonIgnore
    private List<Order> orderList= new ArrayList<>();

    public Food(String name, String description, Double price, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }


    public Food() {
    }

    public Restaurant getRestaurantId() {
        return restaurantId;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setRestaurantId(Restaurant restaurantId) {
        this.restaurantId = restaurantId;
    }

    public void addOrder(Order order){
        orderList.add(order);
    }
    public Food(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getIdFood() {
        return idFood;
    }

    public Category getCategory() {
        return category;
    }


    @Override
    public Double calculatePrice() {
        return this.getPrice();
    }
}
