package org.example.demo.orderservice.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.demo.orderservice.api.events.OrderDetails;
import org.example.demo.orderservice.domain.OrderEvent;
import org.example.demo.orderservice.messaging.OrderEventProducer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderEventListener {
    private final OrderEventProducer orderEventProducer;
    private final ObjectMapper objectMapper;
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleOrderEvent(OrderEvent orderEvent){
        try {
            OrderDetails orderDetails = objectMapper.readValue(orderEvent.getAttributes(), OrderDetails.class);
            orderEventProducer.order("order", orderDetails);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
