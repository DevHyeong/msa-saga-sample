package org.example.demo.orderservice.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.demo.orderservice.api.events.OrderDetails;
import org.example.demo.orderservice.api.events.OrderLineItem;
import org.example.demo.orderservice.enums.OrderEventType;
import org.example.demo.orderservice.exception.InvalidMenuItemIdException;
import org.example.demo.orderservice.exception.RestaurantNotFoundException;
import org.example.demo.orderservice.messaging.OrderEventProducer;
import org.example.demo.orderservice.repository.OrderEventRepository;
import org.example.demo.orderservice.repository.OrderRepository;
import org.example.demo.orderservice.repository.RestaurantRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ApplicationEventPublisher eventPublisher;
    private final ObjectMapper objectMapper;
    private final OrderRepository orderRepository;
    private final OrderEventRepository orderEventRepository;
    private final RestaurantRepository restaurantRepository;
    /**
     *  TO DO: 트랜잭셔널 메시징을 활용하여 DB와 메시징 생성을 하나의 트랜잭션 단위로 묶어야함.
     */
    @Transactional
    public Order createOrder(Long consumerId, Long restaurantId,
                             List<MenuItemIdAndQuantity> lineItems){
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));

        List<OrderLineItem> orderLineItems = makeOrderLineItems(lineItems, restaurant);

        Order order = new Order(consumerId, restaurantId, orderLineItems);
        orderRepository.save(order);

        OrderDetails orderDetails = new OrderDetails(consumerId, restaurantId, orderLineItems, order.getOrderTotalPrice());
        try {
            String attributes = objectMapper.writeValueAsString(orderDetails);
            OrderEvent orderEvent = new OrderEvent(OrderEventType.CREATE, order.getId(), attributes);
            orderEventRepository.save(orderEvent);
            eventPublisher.publishEvent(orderEvent);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return order;
    }

    private List<OrderLineItem> makeOrderLineItems(
            List<MenuItemIdAndQuantity> lineItems, Restaurant restaurant){
        return lineItems.stream().map(e-> {
            MenuItem mi = restaurant.findMenuItem(Long.parseLong(e.getMenuItemId()))
                    .orElseThrow(() -> new InvalidMenuItemIdException(e.getMenuItemId()));
            return new OrderLineItem(e.getMenuItemId(), mi.getName(), mi.getPrice(), e.getQuantity());
        }).collect(Collectors.toList());
    }

}
