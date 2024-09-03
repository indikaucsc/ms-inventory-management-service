package com.pharmacy.ms.inventory.management.dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ProductRequestDto {
    private String name;
    private int quantity;
    private BigDecimal price;
    private LocalDate expirationDate;

    // Getters and Setters
}

