package org.example.demo.orderservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemIdAndQuantity {
    private String menuItemId;
    private int quantity;
}
