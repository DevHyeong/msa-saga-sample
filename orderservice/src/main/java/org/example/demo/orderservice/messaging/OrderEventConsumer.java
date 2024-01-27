package org.example.demo.orderservice.messaging;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderEventConsumer {
    private static final String TOPIC_NAME = "";
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = TOPIC_NAME)
    public void revise(String message){
        try{

        }catch (Exception e){

        }
    }
}
