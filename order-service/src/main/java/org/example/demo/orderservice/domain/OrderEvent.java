package org.example.demo.orderservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.demo.orderservice.enums.OrderEventType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
public class OrderEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private OrderEventType eventType;
    private Long orderId;
    private String attributes;
    private boolean published;
    private LocalDateTime publishedAt;
    private LocalDateTime createdAt;

    public OrderEvent(OrderEventType eventType, Long orderId, String attributes){
        this.eventType = eventType;
        this.orderId = orderId;
        this.attributes = attributes;
        this.createdAt = LocalDateTime.now();
    }

}
