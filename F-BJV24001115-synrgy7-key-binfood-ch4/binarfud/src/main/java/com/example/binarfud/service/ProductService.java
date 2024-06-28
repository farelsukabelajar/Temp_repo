package com.example.binarfud.service;

import com.example.binarfud.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<Product> findAll();
    Product findById(UUID id);
    Product save(Product product);
    void deleteById(UUID id);
    Page<Product> findPaginated(int page, int size);
    void updatePrice(UUID productId, double newPrice);
}
