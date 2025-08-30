package com.hvaghani.ecommerce.ecommerce_restapi.services.impl;

import com.hvaghani.ecommerce.ecommerce_restapi.dto.ProductDto;
import com.hvaghani.ecommerce.ecommerce_restapi.entities.Product;
import com.hvaghani.ecommerce.ecommerce_restapi.exceptions.ResourceNotFoundException;
import com.hvaghani.ecommerce.ecommerce_restapi.repositories.ProductRepository;
import com.hvaghani.ecommerce.ecommerce_restapi.services.ProductService;
import com.hvaghani.ecommerce.ecommerce_restapi.services.utilities.PropertyCopyUtils;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Data
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private ModelMapper modelMapper;

    @Override
    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID: " + id + " doesn't exist!"));
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        return allProducts
                .stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product toSave = modelMapper.map(productDto, Product.class);
        Product savedProduct = productRepository.save(toSave);
        return modelMapper.map(savedProduct, ProductDto.class);
    }

    @Override
    public ProductDto updateProduct(Long productId, ProductDto productDto) {
        if (productId == null) {
            throw new IllegalArgumentException("Product ID must not be null for update!");
        }
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID: " + productId + " not found!"));
        log.info("Product with ID {} found and trying to update the product", productId);
        Product sourceProduct = modelMapper.map(productDto, Product.class);
        PropertyCopyUtils.copyNonNullProperties(sourceProduct, existingProduct);
        existingProduct.setLastUpdatedAt(LocalDateTime.now());
        Product updatedProduct = productRepository.save(existingProduct);
        return modelMapper.map(updatedProduct, ProductDto.class);
    }

    @Override
    public void deleteProduct(Long id) {
        log.info("Trying to delete the product with ID: {}", id);
        productRepository.deleteById(id);
        log.info("Product successufully deleted with ID: {}", id);
    }
}
