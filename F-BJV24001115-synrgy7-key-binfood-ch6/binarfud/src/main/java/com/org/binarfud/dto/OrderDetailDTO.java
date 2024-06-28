package com.org.binarfud.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
    private UUID id;
    private int qty;
    private double price;
    private UUID productId;
    private UUID orderId;
}
