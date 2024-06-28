package com.example.binarfud.service;

import com.example.binarfud.dto.ProductDTO;
import com.example.binarfud.model.Product;
import com.example.binarfud.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDTO> findAll() {
        logger.info("Fetching all products");
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::convertToProductDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO findById(UUID id) {
        logger.debug("Fetching product with id: {}", id);
        Product product = productRepository.findById(id).orElse(null);
        return product != null ? convertToProductDTO(product) : null;
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        logger.info("Saving product: {}", productDTO.getProductName());
        Product product = convertToProduct(productDTO);
        Product savedProduct = productRepository.save(product);
        return convertToProductDTO(savedProduct);
    }

    @Override
    public void deleteById(UUID id) {
        logger.warn("Deleting product with id: {}", id);
        productRepository.deleteById(id);
    }

    @Override
    public Page<ProductDTO> findPaginated(int page, int size) {
        logger.debug("Fetching products with pagination - page: {}, size: {}", page, size);
        Page<Product> productPage = productRepository.findAll(PageRequest.of(page, size));
        return productPage.map(this::convertToProductDTO);
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

    private ProductDTO convertToProductDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setProductId(product.getProductId());
        dto.setProductName(product.getProductName());
        dto.setPrice(product.getPrice());
        dto.setMerchantId(product.getMerchant().getMerchantId());
        return dto;
    }

    private Product convertToProduct(ProductDTO dto) {
        Product product = new Product();
        product.setProductId(dto.getProductId());
        product.setProductName(dto.getProductName());
        product.setPrice(dto.getPrice());
        // You may need to fetch Merchant entity and set it here
        return product;
    }
}
