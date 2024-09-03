
package com.pharmacy.ms.inventory.management.service;

import com.pharmacy.ms.inventory.management.dto.request.ProductRequestDto;
import com.pharmacy.ms.inventory.management.dto.response.ProductResponseDto;
import com.pharmacy.ms.inventory.management.model.ProductEntity;
import com.pharmacy.ms.inventory.management.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductResponseDto createProduct(ProductRequestDto requestDto) {
        ProductEntity product = new ProductEntity();
        product.setName(requestDto.getName());
        product.setQuantity(requestDto.getQuantity());
        product.setPrice(requestDto.getPrice());
        product.setExpirationDate(requestDto.getExpirationDate());

        ProductEntity savedProduct = productRepository.save(product);
        return mapToResponseDto(savedProduct);
    }

    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    private ProductResponseDto mapToResponseDto(ProductEntity product) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setQuantity(product.getQuantity());
        dto.setPrice(product.getPrice());
        dto.setExpirationDate(product.getExpirationDate());
        dto.setCreatedAt(product.getCreatedAt());
        return dto;
    }
}

