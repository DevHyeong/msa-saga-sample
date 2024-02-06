package org.example.demo.orderservice.domain;

import lombok.Getter;
import org.example.demo.common.Money;
import org.example.demo.orderservice.api.events.OrderLineItem;

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

    public Money getOrderTotalPrice(){
        return orderLineItems.stream().map(e-> e.getTotal())
                .reduce(Money.ZERO, Money::add);
    }

}
