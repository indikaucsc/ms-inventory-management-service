package com.pharmacy.ms.inventory.management.controller;

import com.pharmacy.ms.inventory.management.dto.request.ProductRequestDto;
import com.pharmacy.ms.inventory.management.dto.response.BulkUploadResponseDto;
import com.pharmacy.ms.inventory.management.dto.response.ProductResponseDto;
import com.pharmacy.ms.inventory.management.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/inventory/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto requestDto) {
        ProductResponseDto responseDto = productService.createProduct(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) {
        ProductResponseDto responseDto = productService.getProductById(id);
        if (responseDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        boolean isDeleted = productService.deleteProduct(id);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDto requestDto) {
        ProductResponseDto updatedProduct = productService.updateProduct(id, requestDto);
        return updatedProduct == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    // Bulk Upload API
    @PostMapping("/bulk-upload")
    public ResponseEntity<BulkUploadResponseDto> bulkUploadProducts(@RequestParam("file") MultipartFile file) {
        BulkUploadResponseDto response = productService.bulkUploadProducts(file);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
