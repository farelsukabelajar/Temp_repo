package com.org.binarfud.service;

import com.org.binarfud.dto.ProductDTO;
import com.org.binarfud.model.Merchant;
import com.org.binarfud.model.Product;
import com.org.binarfud.repository.MerchantRepository;
import com.org.binarfud.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MerchantRepository merchantRepository;

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public ProductDTO getProductById(UUID id) {
        return productRepository.findById(id).map(this::convertToDTO).orElse(null);
    }

    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        product = productRepository.save(product);
        return convertToDTO(product);
    }

    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }

    private ProductDTO convertToDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .merchantId(product.getMerchant().getId())
                .build();
    }

    private Product convertToEntity(ProductDTO productDTO) {
        Merchant merchant = merchantRepository.findById(productDTO.getMerchantId()).orElse(null);
        return Product.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .merchant(merchant)
                .build();
    }
}
