package org.example.demo.orderservice.domain;

import lombok.RequiredArgsConstructor;
import org.example.demo.orderservice.api.events.OrderDetails;
import org.example.demo.orderservice.api.events.OrderLineItem;
import org.example.demo.orderservice.exception.InvalidMenuItemIdException;
import org.example.demo.orderservice.exception.RestaurantNotFoundException;
import org.example.demo.orderservice.messaging.OrderEventProducer;
import org.example.demo.orderservice.repository.OrderRepository;
import org.example.demo.orderservice.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderEventProducer orderEventProducer;
    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;

    //TO DO: 트랜잭셔널 메시징을 활용하여 DB와 메시징 생성을 하나의 트랜잭션 단위로 묶어야함.
    public Order createOrder(Long consumerId, Long restaurantId,
                             List<MenuItemIdAndQuantity> lineItems){
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));

        List<OrderLineItem> orderLineItems = makeOrderLineItems(lineItems, restaurant);

        Order order = new Order(consumerId, restaurantId, orderLineItems);
        orderRepository.save(order);

        new OrderDetails(consumerId, restaurantId, orderLineItems, order.getOrderTotalPrice());

        return order;
    }

    private List<OrderLineItem> makeOrderLineItems(
            List<MenuItemIdAndQuantity> lineItems, Restaurant restaurant){
        return lineItems.stream().map(e-> {
            MenuItem mi = restaurant.findMenuItem(e.getMenuItemId())
                    .orElseThrow(() -> new InvalidMenuItemIdException(e.getMenuItemId()));
            return new OrderLineItem(e.getMenuItemId(), mi.getName(), mi.getPrice(), e.getQuantity());
        }).collect(Collectors.toList());
    }

}
