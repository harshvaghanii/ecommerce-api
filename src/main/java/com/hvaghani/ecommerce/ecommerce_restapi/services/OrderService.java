package com.hvaghani.ecommerce.ecommerce_restapi.services;

import com.hvaghani.ecommerce.ecommerce_restapi.dto.OrderDto;
import com.hvaghani.ecommerce.ecommerce_restapi.dto.OrderItemDto;

import java.util.List;

public interface OrderService {

    OrderDto findById(Long id);

    List<OrderDto> getOrdersByUser(Long userId);

    OrderDto createOrder(Long cartId);

    void cancelOrder(Long orderId);

    OrderItemDto findOrderItemById(Long id);

}
