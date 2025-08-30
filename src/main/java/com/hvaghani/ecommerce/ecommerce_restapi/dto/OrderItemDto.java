package com.hvaghani.ecommerce.ecommerce_restapi.dto;

import lombok.Data;

@Data
public class OrderItemDto {
    private Long id;
    private ProductDto productDto;
    private Integer quantity;
    private Double price;
}
