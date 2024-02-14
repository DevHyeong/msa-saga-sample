package org.example.demo.orderservice.api.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.demo.common.Money;

import javax.persistence.*;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OrderLineItem {

    private String menuItemId;
    private String name;
    @Embedded
    @AttributeOverrides(@AttributeOverride(name="amount", column=@Column(name="price")))
    private Money price;
    private int quantity;

    public Money getTotal(){
        return price.multiply(quantity);
    }
}
