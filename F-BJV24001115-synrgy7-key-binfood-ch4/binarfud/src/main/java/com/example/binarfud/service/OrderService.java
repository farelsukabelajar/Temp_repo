package com.example.binarfud.service;

import com.example.binarfud.dto.OrderDTO;

import java.util.List;
import java.util.UUID;
import java.util.Date;

public interface OrderService {
    List<OrderDTO> getOrderDetailsByUser(UUID userId);
    List<OrderDTO> getOrdersByMerchantAndDateRange(UUID merchantId, Date startDate, Date endDate);
    OrderDTO save(OrderDTO orderDTO);
    void deleteById(UUID orderId);
    OrderDTO findById(UUID orderId);
}
