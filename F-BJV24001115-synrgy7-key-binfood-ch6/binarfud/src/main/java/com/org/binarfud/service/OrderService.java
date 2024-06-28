package com.org.binarfud.service;

import com.org.binarfud.dto.OrderDTO;
import com.org.binarfud.model.Order;
import com.org.binarfud.model.User;
import com.org.binarfud.repository.OrderRepository;
import com.org.binarfud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public OrderDTO getOrderById(UUID id) {
        return orderRepository.findById(id).map(this::convertToDTO).orElse(null);
    }

    public OrderDTO saveOrder(OrderDTO orderDTO) {
        Order order = convertToEntity(orderDTO);
        order = orderRepository.save(order);
        return convertToDTO(order);
    }

    public void deleteOrder(UUID id) {
        orderRepository.deleteById(id);
    }

    private OrderDTO convertToDTO(Order order) {
        return OrderDTO.builder()
                .orderId(order.getOrderId())
                .destinationAddress(order.getDestinationAddress())
                .orderTime(order.getOrderTime())
                .isComplete(order.getIsComplete())
                .userId(order.getUser().getUsersId())
                .build();
    }

    private Order convertToEntity(OrderDTO orderDTO) {
        User user = userRepository.findById(orderDTO.getUserId()).orElse(null);
        return Order.builder()
                .orderId(orderDTO.getOrderId())
                .destinationAddress(orderDTO.getDestinationAddress())
                .orderTime(orderDTO.getOrderTime())
                .isComplete(orderDTO.getIsComplete())
                .user(user)
                .build();
    }
}
