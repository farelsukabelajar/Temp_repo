package com.example.binarfud.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class ProductDTO {
    private UUID productId;
    private String productName;
    private double price;
    private UUID merchantId;
}
