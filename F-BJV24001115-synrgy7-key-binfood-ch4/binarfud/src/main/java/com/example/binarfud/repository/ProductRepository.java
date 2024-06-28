package com.example.binarfud.repository;

import com.example.binarfud.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Page<Product> findAll(Pageable pageable);

    @Procedure(name = "Product.updatePrice")
    void updateProductPrice(@Param("productId") UUID productId, @Param("newPrice") double newPrice);
}
