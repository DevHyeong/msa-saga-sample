package org.example.demo.orderservice.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderState orderState;
    private List<OrderLineItem> orderLineItems;
    private Long consumerId;
    private Long restaurantId;

    public Order(Long consumerId, Long restaurantId, List<OrderLineItem> orderLineItems){
        this.consumerId = consumerId;
        this.restaurantId = restaurantId;
        this.orderLineItems = orderLineItems;
        this.orderState = OrderState.APPROVAL_PENDING;
    }

}
