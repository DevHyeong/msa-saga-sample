package org.example.demo.orderservice.repository;

import org.example.demo.orderservice.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
