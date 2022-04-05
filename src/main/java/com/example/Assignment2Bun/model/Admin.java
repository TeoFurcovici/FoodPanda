package com.example.Assignment2Bun.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Admin extends User implements Observer{
    @OneToOne
    @JsonIgnore
    private Restaurant restaurant;
    public Admin( String firstName, String lastName, String email, String password,String username,String isAdmin) {
        super( firstName, lastName, email, password,username,isAdmin);
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Admin() {
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    @Override
    public void update(final String m) {
        System.out.println("Admin with username :: " + this.getUsername() + "  "+ m);
    }
}
