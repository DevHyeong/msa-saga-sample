package org.example.demo.orderservice.api.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.demo.common.Money;

import javax.persistence.*;

@Embeddable
@AllArgsConstructor
@Getter
public class OrderLineItem {

    private String menuItemId;
    private String name;
    private Money price;
    private int quantity;

    public Money getTotal(){
        return price.multiply(quantity);
    }
}
