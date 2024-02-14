package org.example.demo.orderservice.repository;

import org.example.demo.orderservice.domain.OrderEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderEventRepository extends JpaRepository<OrderEvent, Long> {
}
