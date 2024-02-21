package org.example.demo.consumerservice.domain;

import lombok.RequiredArgsConstructor;
import org.example.demo.common.Money;
import org.example.demo.consumerservice.exception.ConsumerNotFoundException;
import org.example.demo.consumerservice.repository.ConsumerRepository;
import org.example.demo.orderservice.api.events.OrderDetails;
import org.example.demo.orderservice.api.events.OrderEvent;
import org.example.demo.orderservice.api.events.OrderEventType;
import org.example.demo.orderservice.api.repository.OrderEventRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumerService {
    private final ApplicationEventPublisher eventPublisher;
    private final ConsumerRepository consumerRepository;
    private final OrderEventRepository orderEventRepository;

    public void validateOrderForConsumer(OrderDetails orderDetails){
        consumerRepository.findById(orderDetails.getConsumerId())
                .orElseThrow(ConsumerNotFoundException::new)
                .validateOrderByConsumer(orderDetails.getOrderTotal());

        OrderEvent orderEvent = new OrderEvent(
                OrderEventType.CREATE,
                orderDetails.getConsumerId()
        );
        orderEventRepository.save(orderEvent);
        eventPublisher.publishEvent(orderEvent);
    }

}
