package com.example.binarfud.service;

import com.example.binarfud.model.Product;
import com.example.binarfud.repository.ProductRepository;
import com.example.binarfud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        logger.info("Fetching all products");
        return productRepository.findAll();
    }

    @Override
    public Product findById(UUID id) {
        logger.debug("Fetching product with id: {}", id);
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product save(Product product) {
        logger.info("Saving product: {}", product.getProductName());
        return productRepository.save(product);
    }

    @Override
    public void deleteById(UUID id) {
        logger.warn("Deleting product with id: {}", id);
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> findPaginated(int page, int size) {
        logger.debug("Fetching products with pagination - page: {}, size: {}", page, size);
        return productRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public void updatePrice(UUID productId, double newPrice) {
        try {
            logger.info("Updating price for product with id: {} to {}", productId, newPrice);
            productRepository.updateProductPrice(productId, newPrice);
        } catch (Exception e) {
            logger.error("Error updating price for product with id: {}", productId, e);
        }
    }
}
