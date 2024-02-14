package org.example.demo.orderservice.messaging;

import org.example.demo.common.Money;
import org.example.demo.orderservice.api.events.OrderDetails;
import org.example.demo.orderservice.api.events.OrderLineItem;
import org.example.demo.orderservice.domain.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderEventProducerTest {

    @Autowired
    private OrderEventProducer orderEventProducer;

    @Test
    void order() {
        String topic = "order";
        Long consumerId = 1L;
        Long restaurantId = 1L;

        List<OrderLineItem> orderLineItems = new ArrayList<>();
        OrderLineItem o1 = new OrderLineItem("1", "김치찌개", new Money(10000), 2);
        OrderLineItem o2 = new OrderLineItem("2", "된장찌개", new Money(10000), 2);

        orderLineItems.add(o1);
        orderLineItems.add(o2);
        Order order = new Order(consumerId, restaurantId, orderLineItems);

        OrderDetails orderDetails = new OrderDetails(consumerId, restaurantId,
                orderLineItems, order.getOrderTotalPrice());

        orderEventProducer.order(topic, orderDetails);
    }
}