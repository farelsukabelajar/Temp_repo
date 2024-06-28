package com.example.binarfud.service;

import com.example.binarfud.dto.ProductDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    List<ProductDTO> findAll();
    ProductDTO findById(UUID id);
    ProductDTO save(ProductDTO productDTO);
    void deleteById(UUID id);
    Page<ProductDTO> findPaginated(int page, int size);
    void updatePrice(UUID productId, double newPrice);
}
