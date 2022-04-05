package com.example.Assignment2Bun.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @Column( nullable = false)
    private String name;
    @Column
    private  String location;
    @Column
    private String zone;
    @OneToOne
    @JsonIgnore
    private Menu menu;
    @JsonIgnore
    @OneToOne
    private Admin admin;
    @JsonIgnore
    @OneToMany
    private List<Order> orders= new ArrayList<>();
    @OneToMany
    private List<Food> foodList= new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }



    public List<Order> getOrders() {
        return orders;
    }

    public Restaurant(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public Restaurant() {

    }

    public Admin getAdmin() {
        return admin;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void addOrder(Order order){
        orders.add(order);
    }


    public void addFood(Food food){
        foodList.add(food);
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }


    public Double getPrice()
    {
        Double price=0.0;
        for (Order order:orders) {
            price+=order.calculatePrice();
        }
        return price;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }
}
