package org.example.demo.orderservice.exception;

public class RestaurantNotFoundException extends RuntimeException {
    public RestaurantNotFoundException(Long restaurantId) {
        super("Restaurant not found with id " + restaurantId);
    }
}
