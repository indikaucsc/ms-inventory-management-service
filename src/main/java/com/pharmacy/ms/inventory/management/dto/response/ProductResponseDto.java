package com.pharmacy.ms.inventory.management.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ProductResponseDto {
    private Long id;
    private String name;
    private int quantity;
    private BigDecimal price;
    private LocalDate expirationDate;
    private LocalDateTime createdAt;

    // Getters and Setters
}


