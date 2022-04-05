package com.example.Assignment2Bun.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "orders")
public class Order implements Subject,BaseComponent {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private  int orderId;
    @Column
    private OrderStatus orderStatus;
    @Column
    @ManyToMany
    private List<Food> foodListOrder= new ArrayList<>();
    @ManyToOne
    @JsonIgnore
    private User client;
    @ManyToOne
    @JsonIgnore
    private  Restaurant restaurant;
    @Convert(converter = ListToStringConverter.class)
    @Transient
    private List<Observer> myObservers = new ArrayList<>();
    @Column
    private  Double totalPrice=0.0;


    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Order(List<Food> foodListOrder, User client, Restaurant restaurant) {
        this.foodListOrder = foodListOrder;
        this.client = client;
        this.restaurant = restaurant;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Order() {
    }
    public void addFoodToOrder(Food food){
        foodListOrder.add(food);
    }

    public void removeFoodFromOrder(Food food){
        foodListOrder.remove(food);
    }

    @Override
    public int hashCode() {
            return Objects.hash(orderId, orderStatus, foodListOrder, client, restaurant);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Order order = (Order) obj;
        return orderId == order.orderId &&
                orderStatus == order.orderStatus &&
                foodListOrder == order.foodListOrder &&
                client == order.client &&
                restaurant == order.restaurant;
    }


    @Override
    public void attach(Observer o) {
        myObservers.add(o);
    }

    @Override
    public void detach(Observer o) {
        myObservers.remove(o);
    }

    @Override
    public void notifyUpdate(String m) {
        for (Observer o: myObservers) {
            o.update(m);
        }
    }

    public int getOrderId() {
        return orderId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public List<Food> getFoodListOrder() {
        return foodListOrder;
    }

    public User getClient() {
        return client;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public List<Observer> getMyObservers() {
        return myObservers;
    }

    @Override
    public Double calculatePrice() {
        for (Food food: foodListOrder) {
            totalPrice+=food.calculatePrice();
        }
        return totalPrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }
}
