package com.hvaghani.ecommerce.ecommerce_restapi.dto;

import lombok.Data;

@Data
public class CartItemDto {
    private Long id;
    private ProductDto product;
    private Integer quantity;
}
