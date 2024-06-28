package com.example.binarfud.service;

import com.example.binarfud.model.Order;
import com.example.binarfud.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getOrderDetailsByUser(UUID userId) {
        return orderRepository.findByUserUserId(userId);
    }

    @Override
    public List<Order> getOrdersByMerchantAndDateRange(UUID merchantId, Date startDate, Date endDate) {
        return orderRepository.findByMerchantAndDateRange(merchantId, startDate, endDate);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteById(UUID orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public Order findById(UUID orderId) {  // Menambahkan implementasi metode ini
        return orderRepository.findById(orderId).orElse(null);
    }
}
