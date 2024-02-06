package org.example.demo.orderservice.api.events;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.demo.common.Money;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OrderDetails {
    private Long consumerId;
    private Long restaurantId;
    private List<OrderLineItem> lineItems;
    private Money orderTotal;
}
