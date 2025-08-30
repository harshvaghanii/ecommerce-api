package com.hvaghani.ecommerce.ecommerce_restapi.services.impl;

import com.hvaghani.ecommerce.ecommerce_restapi.dto.CartDto;
import com.hvaghani.ecommerce.ecommerce_restapi.dto.CartItemDto;
import com.hvaghani.ecommerce.ecommerce_restapi.entities.Cart;
import com.hvaghani.ecommerce.ecommerce_restapi.entities.CartItem;
import com.hvaghani.ecommerce.ecommerce_restapi.entities.Product;
import com.hvaghani.ecommerce.ecommerce_restapi.entities.User;
import com.hvaghani.ecommerce.ecommerce_restapi.exceptions.ResourceNotFoundException;
import com.hvaghani.ecommerce.ecommerce_restapi.repositories.CartItemRepository;
import com.hvaghani.ecommerce.ecommerce_restapi.repositories.CartRepository;
import com.hvaghani.ecommerce.ecommerce_restapi.repositories.ProductRepository;
import com.hvaghani.ecommerce.ecommerce_restapi.repositories.UserRepository;
import com.hvaghani.ecommerce.ecommerce_restapi.services.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {
    private final UserRepository userRepository;

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public CartDto findById(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart with ID: " + id + " not found!"));
        return modelMapper.map(cart, CartDto.class);
    }

    @Override
    public CartDto createCart(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID: " + userId + " not found! Cannot create the cart for an invalid User!"));
        Cart cart = Cart
                .builder()
                .user(user)
                .items(new ArrayList<>())
                .build();
        return modelMapper.map(cartRepository.save(cart), CartDto.class);
    }

    @Override
    public CartDto addItem(Long cartId, Long productId, Integer quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart with ID: " + cartId + " not found!"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID: " + productId + " not found!"));

        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .product(product)
                .quantity(quantity)
                .build();

        cartItemRepository.save(cartItem);
        cart.getItems().add(cartItem);
        cart.setLastUpdatedAt(LocalDateTime.now());
        return modelMapper.map(cartRepository.save(cart), CartDto.class);
    }

    @Override
    public CartDto removeItem(Long cartId, Long cartItemId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart with ID: " + cartId + " not found!"));

        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new ResourceNotFoundException("CartItem with ID: " + cartItemId + " not found!"));

        if (!cart.getItems().contains(cartItem)) {
            throw new ResourceNotFoundException("CartItem with ID: " + cartItemId + " does not belong to Cart ID: " + cartId);
        }

        if (cartItem.getQuantity() > 1) {
            cartItem.setQuantity(cartItem.getQuantity() - 1);
            cartItemRepository.save(cartItem);
            log.info("Reduced quantity of CartItem ID {} by 1", cartItemId);
        } else {
            cart.getItems().remove(cartItem);
            cartItemRepository.delete(cartItem);
            log.info("Removed CartItem ID {} from Cart ID {}", cartItemId, cartId);
        }

        cart.setLastUpdatedAt(LocalDateTime.now());
        cartRepository.save(cart);

        return modelMapper.map(cart, CartDto.class);
    }

    @Override
    public void clearCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart with ID: " + cartId + " not found!"));
        cartItemRepository.deleteAll(cart.getItems());
        cart.getItems().clear();
        cart.setLastUpdatedAt(LocalDateTime.now());
        cartRepository.save(cart);
        log.info("Cleared all items from cart with ID: {}", cartId);
    }

    @Override
    public void deleteCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart with ID: " + cartId + " not found!"));

        cartItemRepository.deleteAll(cart.getItems());
        cartRepository.delete(cart);

        log.info("Deleted cart ID {}", cartId);

    }

    @Override
    public CartItemDto findCartItemById(Long id) {
        CartItem item = cartItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CartItem with ID: " + id + " not found!"));

        return modelMapper.map(item, CartItemDto.class);
    }

    @Override
    public void deleteCartItem(Long id) {
        CartItem item = cartItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CartItem with ID: " + id + " not found!"));

        cartItemRepository.delete(item);
        log.info("Deleted cart item ID {}", id);
    }
}
