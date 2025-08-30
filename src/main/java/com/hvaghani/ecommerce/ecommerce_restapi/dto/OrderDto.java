package com.hvaghani.ecommerce.ecommerce_restapi.dto;

import com.hvaghani.ecommerce.ecommerce_restapi.entities.enums.OrderStatus;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private Long userId;
    private OrderStatus orderStatus;
    private Double amount;
    private List<OrderItemDto> items;
}
