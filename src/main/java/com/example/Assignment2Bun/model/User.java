package com.example.Assignment2Bun.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // This tells Hibernate to make a table out of this class
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    private String username;
    private String password;
    private String isAdmin;

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToMany
    @JsonIgnore
    private List<Order> clientOrders=new ArrayList<>();
    public User() {
    }

    public String getUsername() {

        return username;
    }

    public void setClientOrders(List<Order> clientOrders) {
        this.clientOrders = clientOrders;
    }

    public User(String firstName, String lastName, String email, String password, String username, String isAdmin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.username = username;
        this.isAdmin=isAdmin;
    }
    public List<Order> getClientOrders() {
        return clientOrders;
    }

    public void addOrder(Order order){
        clientOrders.add(order);
    }
    public void removeOrder(Order order){
           clientOrders.remove(order);
    }
    public String getPassword() {
        return password;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }





    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}
