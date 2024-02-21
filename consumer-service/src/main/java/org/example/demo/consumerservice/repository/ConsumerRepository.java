package org.example.demo.consumerservice.repository;

import org.example.demo.consumerservice.domain.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumerRepository extends JpaRepository<Consumer, Long> {
}
