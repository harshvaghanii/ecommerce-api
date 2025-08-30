package com.hvaghani.ecommerce.ecommerce_restapi.services;

import com.hvaghani.ecommerce.ecommerce_restapi.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto findById(Long id);

    List<ProductDto> getAllProducts();

    ProductDto createProduct(ProductDto productDto);

    ProductDto updateProduct(Long id, ProductDto productDto);

    void deleteProduct(Long id);

}
