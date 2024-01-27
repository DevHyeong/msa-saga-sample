package org.example.demo.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CreateOrderRequest {
    private Long restaurantId;
    private Long consumerId;
    private List<LineItem> lineItems;

    public CreateOrderRequest(Long restaurantId, Long consumerId, List<LineItem> lineItems){
        this.restaurantId = restaurantId;
        this.consumerId = consumerId;
        this.lineItems = lineItems;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LineItem {
        private String menuItemId;
        private int quantity;
    }
}
