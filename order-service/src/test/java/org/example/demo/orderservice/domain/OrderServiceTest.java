package org.example.demo.orderservice.domain;

import org.example.demo.common.Money;
import org.example.demo.orderservice.api.events.OrderLineItem;
import org.example.demo.orderservice.repository.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Test
    void testCreateOrder() {
        Long consumerId = 1L;
        Long restaurantId = 1L;

        MenuItemIdAndQuantity m1 = new MenuItemIdAndQuantity("1", 100);
        MenuItemIdAndQuantity m2 = new MenuItemIdAndQuantity("2", 100);

        MenuItem mi1 = new MenuItem(1L, "김치찌개", new Money(12000));
        MenuItem mi2 = new MenuItem(2L, "된장찌개", new Money(12000));

        Restaurant restaurant = new Restaurant("잇츠미미",
                Arrays.asList(mi1, mi2));

        restaurantRepository.save(restaurant);
        orderService.createOrder(consumerId, restaurantId,
                Arrays.asList(m1, m2));
    }
}