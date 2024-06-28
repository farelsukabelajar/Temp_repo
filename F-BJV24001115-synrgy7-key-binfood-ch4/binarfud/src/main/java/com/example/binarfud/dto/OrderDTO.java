package com.example.binarfud.dto;

import lombok.Data;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class OrderDTO {
    private UUID orderId;
    private String destinationAddress;
    private Date orderTime;
    private boolean complete;
    private UUID userId;
    private UUID merchantId;
    private List<OrderDetailDTO> orderDetails;
}
