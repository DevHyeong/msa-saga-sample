package org.example.demo.consumerservice.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.demo.consumerservice.domain.ConsumerService;
import org.example.demo.consumerservice.exception.ConsumerNotFoundException;
import org.example.demo.orderservice.api.events.OrderDetails;
import org.example.demo.orderservice.api.events.OrderEvent;
import org.example.demo.orderservice.api.events.OrderEventType;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumerEventConsumer {
    private final ApplicationEventPublisher eventPublisher;
    private final ConsumerService consumerService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${kafka.topic.order-name}")
    public void validateOrder(String message){
        try {
            OrderDetails orderDetails = objectMapper.readValue(message, OrderDetails.class);
            OrderEvent orderEvent = new OrderEvent(OrderEventType.CREATE, orderDetails.getOrderId(), message);
            eventPublisher.publishEvent(orderEvent);
            consumerService.validateOrderForConsumer(orderDetails);
        } catch (JsonProcessingException | ConsumerNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
