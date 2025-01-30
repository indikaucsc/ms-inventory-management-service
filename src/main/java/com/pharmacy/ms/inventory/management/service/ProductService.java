package com.pharmacy.ms.inventory.management.service;

import com.pharmacy.ms.inventory.management.dto.request.ProductRequestDto;
import com.pharmacy.ms.inventory.management.dto.response.BulkUploadResponseDto;
import com.pharmacy.ms.inventory.management.dto.response.ProductResponseDto;
import com.pharmacy.ms.inventory.management.model.ProductEntity;
import com.pharmacy.ms.inventory.management.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductResponseDto createProduct(ProductRequestDto requestDto) {
        ProductEntity product = new ProductEntity();
        if (requestDto.getId() > 0) {
            product.setId(requestDto.getId());
        }
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

    public ProductResponseDto getProductById(Long id) {
        return productRepository.findById(id).map(this::mapToResponseDto).orElse(null);
    }

    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(requestDto.getName());
                    product.setQuantity(requestDto.getQuantity());
                    product.setPrice(requestDto.getPrice());
                    product.setExpirationDate(requestDto.getExpirationDate());
                    return mapToResponseDto(productRepository.save(product));
                })
                .orElse(null);
    }

    public BulkUploadResponseDto bulkUploadProducts(MultipartFile file) {
        List<String> errors = new ArrayList<>();
        int successCount = 0;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Skip header
                    continue;
                }

                String[] fields = line.split(",");
                if (fields.length < 4) {
                    errors.add("Invalid data format: " + line);
                    continue;
                }

                try {
                    ProductRequestDto dto = new ProductRequestDto();
                    dto.setName(fields[0].trim());
                    dto.setQuantity(Integer.parseInt(fields[1].trim()));
                    dto.setPrice(BigDecimal.valueOf(Double.parseDouble(fields[2].trim())));
                    dto.setExpirationDate(LocalDate.parse(fields[3].trim()));
                    createProduct(dto);
                    successCount++;
                } catch (Exception e) {
                    errors.add("Failed to process line: " + line + " - " + e.getMessage());
                }
            }
        } catch (Exception e) {
            errors.add("Error reading file: " + e.getMessage());
        }

        return new BulkUploadResponseDto(successCount, errors.size(), errors);
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
