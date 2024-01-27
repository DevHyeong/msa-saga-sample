package org.example.demo.orderservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;

@Embeddable
@AllArgsConstructor
@Getter
public class OrderLineItem {

    private String menuItemId;
    private String name;
    private int price;
    private int quantity;
}
