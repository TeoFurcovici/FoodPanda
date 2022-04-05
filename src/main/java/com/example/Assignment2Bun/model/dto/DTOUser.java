package com.example.Assignment2Bun.model.dto;

public class DTOUser {// doar cu ce am nevoie in json

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String username;
    private String restaurantName;
    private String isAdmin;

    public String getIsAdmin() {
        return isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
