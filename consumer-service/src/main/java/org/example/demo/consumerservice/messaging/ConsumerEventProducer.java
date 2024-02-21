package org.example.demo.consumerservice.messaging;

import lombok.RequiredArgsConstructor;
import org.example.demo.orderservice.api.events.OrderDetails;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumerEventProducer {
    private final KafkaTemplate kafkaTemplate;

    public void order(String topic, OrderDetails orderDetails){
        kafkaTemplate.send(topic, orderDetails);

    }

    public void reviseOrder(String topic, Long orderId){
        kafkaTemplate.send(topic, orderId);

    }

}
