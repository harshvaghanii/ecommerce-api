package com.hvaghani.ecommerce.ecommerce_restapi.services;

import com.hvaghani.ecommerce.ecommerce_restapi.dto.CartDto;
import com.hvaghani.ecommerce.ecommerce_restapi.dto.CartItemDto;

public interface CartService {

    CartDto findById(Long id);

    CartDto createCart(Long userId);

    CartDto addItem(Long cartId, Long productId, Integer quantity);

    CartDto removeItem(Long cartId, Long cartItemId);

    void clearCart(Long cartId);

    void deleteCart(Long cartId);

    CartItemDto findCartItemById(Long id);

    void deleteCartItem(Long id);

}
