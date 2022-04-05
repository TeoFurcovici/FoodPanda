package com.example.Assignment2Bun.model.dto;


public class OrderDTO {
    private int orderId;
    private String foodNames;
    private String restaurantName;
    private String clientUsername;

    public String getFoodNames() {
        return foodNames;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public int getOrderId() {
        return orderId;
    }
}
