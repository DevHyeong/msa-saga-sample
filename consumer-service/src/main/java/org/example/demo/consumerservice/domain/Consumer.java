package org.example.demo.consumerservice.domain;

import lombok.Getter;
import org.example.demo.common.Money;

import javax.persistence.*;

@Entity
@Table(name = "consumers")
@Getter
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public void validateOrderByConsumer(Money orderTotal){

    }
}
