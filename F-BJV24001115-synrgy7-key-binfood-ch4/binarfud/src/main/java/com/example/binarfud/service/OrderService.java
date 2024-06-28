package com.example.binarfud.service;

import com.example.binarfud.model.Order;

import java.util.List;
import java.util.UUID;
import java.util.Date;

public interface OrderService {
    List<Order> getOrderDetailsByUser(UUID userId);
    List<Order> getOrdersByMerchantAndDateRange(UUID merchantId, Date startDate, Date endDate);
    Order save(Order order);
    void deleteById(UUID orderId);
    Order findById(UUID orderId);  // Menambahkan metode ini
}
