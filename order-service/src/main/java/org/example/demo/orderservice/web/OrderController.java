package org.example.demo.orderservice.web;

import lombok.RequiredArgsConstructor;
import org.example.demo.orderservice.domain.MenuItemIdAndQuantity;
import org.example.demo.orderservice.domain.Order;
import org.example.demo.orderservice.domain.OrderService;
import org.example.demo.orderservice.dto.CreateOrderRequest;
import org.example.demo.orderservice.dto.CreateOrderResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("")
    public CreateOrderResponse create(@RequestBody CreateOrderRequest request){
        Order order = orderService.createOrder(request.getConsumerId(),
                request.getRestaurantId(),
                request.getLineItems().stream()
                        .map(e-> new MenuItemIdAndQuantity(e.getMenuItemId(), e.getQuantity()))
                        .collect(Collectors.toList()));
        return new CreateOrderResponse(order.getId());
    }
}
