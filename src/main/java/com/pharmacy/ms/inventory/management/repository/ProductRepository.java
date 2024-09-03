package com.pharmacy.ms.inventory.management.repository;


import com.pharmacy.ms.inventory.management.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}