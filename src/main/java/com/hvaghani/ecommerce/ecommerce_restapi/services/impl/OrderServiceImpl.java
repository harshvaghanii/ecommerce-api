package com.hvaghani.ecommerce.ecommerce_restapi.services.impl;

import com.hvaghani.ecommerce.ecommerce_restapi.dto.OrderDto;
import com.hvaghani.ecommerce.ecommerce_restapi.dto.OrderItemDto;
import com.hvaghani.ecommerce.ecommerce_restapi.entities.Cart;
import com.hvaghani.ecommerce.ecommerce_restapi.entities.Order;
import com.hvaghani.ecommerce.ecommerce_restapi.entities.OrderItem;
import com.hvaghani.ecommerce.ecommerce_restapi.entities.User;
import com.hvaghani.ecommerce.ecommerce_restapi.entities.enums.OrderStatus;
import com.hvaghani.ecommerce.ecommerce_restapi.exceptions.ResourceNotFoundException;
import com.hvaghani.ecommerce.ecommerce_restapi.repositories.CartItemRepository;
import com.hvaghani.ecommerce.ecommerce_restapi.repositories.OrderRepository;
import com.hvaghani.ecommerce.ecommerce_restapi.repositories.ProductRepository;
import com.hvaghani.ecommerce.ecommerce_restapi.services.CartService;
import com.hvaghani.ecommerce.ecommerce_restapi.services.OrderService;
import com.hvaghani.ecommerce.ecommerce_restapi.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final CartService cartService;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public OrderDto findById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order with ID: " + id + " not found!"));
        return modelMapper.map(order, OrderDto.class);
    }

    @Override
    public List<OrderDto> getOrdersByUser(Long userId) {
        User user = modelMapper.map(userService.findById(userId), User.class);
        List<Order> orders = orderRepository.findByUser(user);
        return orders
                .stream()
                .map((element) -> modelMapper.map(element, OrderDto.class))
                .toList();
    }

    @Override
    public OrderDto createOrder(Long cartId) {
        Cart cart = modelMapper.map(cartService.findById(cartId), Cart.class);

        if (cart.getItems().isEmpty()) {
            throw new IllegalStateException("Cart is empty, cannot create order from an empty cart!");
        }

        Order order = Order
                .builder()
                .user(cart.getUser())
                .status(OrderStatus.PENDING)
                .items(new ArrayList<>())
                .build();

        // Setting the List<OrderItems>
        List<OrderItem> orderItems = cart
                .getItems()
                .stream()
                .map(cartItem -> OrderItem
                        .builder()
                        .order(order)
                        .product(cartItem.getProduct())
                        .quantity(cartItem.getQuantity())
                        .price(cartItem.getPrice())
                        .build()
                )
                .toList();
        order.setItems(orderItems);
        order.setAmount(calculatePrice(orderItems));


        Order savedOrder = orderRepository.save(order);
        return modelMapper.map(savedOrder, OrderDto.class);
    }

    @Override
    public void cancelOrder(Long orderId) {

    }

    @Override
    public OrderItemDto findOrderItemById(Long id) {
        return null;
    }

    private double calculatePrice(List<OrderItem> items) {
        if (items == null || items.isEmpty()) return 0;

        return items
                .stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }

}
