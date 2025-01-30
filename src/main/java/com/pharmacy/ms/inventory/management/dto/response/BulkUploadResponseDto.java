package com.pharmacy.ms.inventory.management.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BulkUploadResponseDto {
    private int successCount;
    private int failedCount;
    private List<String> errors;
}

