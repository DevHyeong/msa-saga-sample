package org.example.demo.orderservice.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderEventProducer {
    private final KafkaTemplate kafkaTemplate;

    public void order(){

    }
}
