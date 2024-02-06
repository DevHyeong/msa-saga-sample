package org.example.demo.orderservice.messaging;

import lombok.RequiredArgsConstructor;
import org.example.demo.orderservice.api.events.OrderDetails;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@RequiredArgsConstructor
public class OrderEventProducer {
    private final KafkaTemplate kafkaTemplate;

    public void order(String topic, OrderDetails orderDetails){
        ListenableFuture<SendResult<Integer, OrderDetails>> sendResult = kafkaTemplate.send(topic, orderDetails);
        sendResult.addCallback(new ListenableFutureCallback<SendResult<Integer, OrderDetails>>() {
            @Override
            public void onFailure(Throwable ex) {

            }

            @Override
            public void onSuccess(SendResult<Integer, OrderDetails> result) {

            }
        });
    }
}
