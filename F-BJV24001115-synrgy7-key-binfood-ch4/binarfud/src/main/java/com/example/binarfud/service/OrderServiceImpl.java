package com.example.binarfud.service;

import com.example.binarfud.dto.OrderDTO;
import com.example.binarfud.model.Order;
import com.example.binarfud.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MerchantService merchantService;

    @Override
    public List<OrderDTO> getOrderDetailsByUser(UUID userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }

        List<Order> orders = orderRepository.findByUserUserId(userId);
        return orders.stream()
                .map(this::convertToOrderDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getOrdersByMerchantAndDateRange(UUID merchantId, Date startDate, Date endDate) {
        if (merchantId == null) {
            throw new IllegalArgumentException("Merchant ID cannot be null");
        }
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Start date and end date cannot be null");
        }

        List<Order> orders = orderRepository.findByMerchantAndDateRange(merchantId, startDate, endDate);
        return orders.stream()
                .map(this::convertToOrderDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO save(OrderDTO orderDTO) {
        if (orderDTO == null) {
            throw new IllegalArgumentException("OrderDTO cannot be null");
        }

        Order order = convertToOrder(orderDTO);
        Order savedOrder = orderRepository.save(order);
        return convertToOrderDTO(savedOrder);
    }

    @Override
    public void deleteById(UUID orderId) {
        if (orderId == null) {
            throw new IllegalArgumentException("Order ID cannot be null");
        }

        orderRepository.deleteById(orderId);
    }

    @Override
    public OrderDTO findById(UUID orderId) {
        if (orderId == null) {
            throw new IllegalArgumentException("Order ID cannot be null");
        }

        Order order = orderRepository.findById(orderId).orElse(null);
        return order != null ? convertToOrderDTO(order) : null;
    }

    private OrderDTO convertToOrderDTO(Order order) {
        if (order == null) {
            return null;
        }

        OrderDTO dto = new OrderDTO();
        dto.setOrderId(order.getOrderId());
        dto.setDestinationAddress(order.getDestinationAddress());
        dto.setOrderTime(order.getOrderTime());
        dto.setComplete(order.isComplete());
        dto.setUserId(order.getUser().getUserId());
        dto.setMerchantId(order.getMerchant().getMerchantId());
        // Convert OrderDetails to OrderDetailDTOs if necessary
        // dto.setOrderDetails(order.getOrderDetails().stream().map(this::convertToOrderDetailDTO).collect(Collectors.toList()));
        return dto;
    }

    private Order convertToOrder(OrderDTO dto) {
        if (dto == null) {
            return null;
        }

        Order order = new Order();
        order.setOrderId(dto.getOrderId());
        order.setDestinationAddress(dto.getDestinationAddress());
        order.setOrderTime(dto.getOrderTime());
        order.setComplete(dto.isComplete());
        // You may need to fetch User and Merchant entities and set them here
        return order;
    }
}
