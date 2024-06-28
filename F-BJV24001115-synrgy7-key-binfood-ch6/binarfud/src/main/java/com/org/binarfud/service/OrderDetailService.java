package com.org.binarfud.service;

import com.org.binarfud.dto.OrderDetailDTO;
import com.org.binarfud.model.Order;
import com.org.binarfud.model.OrderDetail;
import com.org.binarfud.model.Product;
import com.org.binarfud.repository.OrderDetailRepository;
import com.org.binarfud.repository.OrderRepository;
import com.org.binarfud.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<OrderDetailDTO> getAllOrderDetails() {
        return orderDetailRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public OrderDetailDTO getOrderDetailById(UUID id) {
        return orderDetailRepository.findById(id).map(this::convertToDTO).orElse(null);
    }

    public OrderDetailDTO saveOrderDetail(OrderDetailDTO orderDetailDTO) {
        OrderDetail orderDetail = convertToEntity(orderDetailDTO);
        orderDetail = orderDetailRepository.save(orderDetail);
        return convertToDTO(orderDetail);
    }

    public void deleteOrderDetail(UUID id) {
        orderDetailRepository.deleteById(id);
    }

    private OrderDetailDTO convertToDTO(OrderDetail orderDetail) {
        return OrderDetailDTO.builder()
                .id(orderDetail.getId())
                .qty(orderDetail.getQty())
                .price(orderDetail.getPrice())
                .productId(orderDetail.getProduct().getId())
                .orderId(orderDetail.getOrder().getOrderId())
                .build();
    }

    private OrderDetail convertToEntity(OrderDetailDTO orderDetailDTO) {
        Product product = productRepository.findById(orderDetailDTO.getProductId()).orElse(null);
        Order order = orderRepository.findById(orderDetailDTO.getOrderId()).orElse(null);
        return OrderDetail.builder()
                .id(orderDetailDTO.getId())
                .qty(orderDetailDTO.getQty())
                .price(orderDetailDTO.getPrice())
                .product(product)
                .order(order)
                .build();
    }
}
