package com.example.binarfud.service;

import com.example.binarfud.dto.OrderDetailDTO;
import com.example.binarfud.model.Order;
import com.example.binarfud.model.OrderDetail;
import com.example.binarfud.model.Product;
import com.example.binarfud.repository.OrderDetailRepository;
import com.example.binarfud.repository.OrderRepository;
import com.example.binarfud.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<OrderDetailDTO> findAll() {
        List<OrderDetail> orderDetails = orderDetailRepository.findAll();
        return orderDetails.stream()
                .map(this::convertToOrderDetailDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDetailDTO findById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("OrderDetail ID cannot be null");
        }

        OrderDetail orderDetail = orderDetailRepository.findById(id).orElse(null);
        return orderDetail != null ? convertToOrderDetailDTO(orderDetail) : null;
    }

    @Override
    public OrderDetailDTO save(OrderDetailDTO orderDetailDTO) {
        if (orderDetailDTO == null) {
            throw new IllegalArgumentException("OrderDetailDTO cannot be null");
        }

        OrderDetail orderDetail = convertToOrderDetail(orderDetailDTO);
        OrderDetail savedOrderDetail = orderDetailRepository.save(orderDetail);
        return convertToOrderDetailDTO(savedOrderDetail);
    }

    @Override
    public void deleteById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("OrderDetail ID cannot be null");
        }

        orderDetailRepository.deleteById(id);
    }

    @Override
    public Page<OrderDetailDTO> findPaginated(int page, int size) {
        Page<OrderDetail> orderDetailPage = orderDetailRepository.findAll(PageRequest.of(page, size));
        return orderDetailPage.map(this::convertToOrderDetailDTO);
    }

    private OrderDetailDTO convertToOrderDetailDTO(OrderDetail orderDetail) {
        if (orderDetail == null) {
            return null;
        }

        OrderDetailDTO dto = new OrderDetailDTO();
        dto.setOrderDetailId(orderDetail.getOrderDetailId());
        dto.setProductId(orderDetail.getProduct().getProductId());
        dto.setOrderId(orderDetail.getOrder().getOrderId());
        dto.setQuantity(orderDetail.getQuantity());
        dto.setTotalPrice(orderDetail.getTotalPrice());
        return dto;
    }

    private OrderDetail convertToOrderDetail(OrderDetailDTO dto) {
        if (dto == null) {
            return null;
        }

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderDetailId(dto.getOrderDetailId());
        orderDetail.setQuantity(dto.getQuantity());
        orderDetail.setTotalPrice(dto.getTotalPrice());

        // Fetch and set Product and Order entities
        Product product = productRepository.findById(dto.getProductId()).orElse(null);
        Order order = orderRepository.findById(dto.getOrderId()).orElse(null);

        orderDetail.setProduct(product);
        orderDetail.setOrder(order);

        return orderDetail;
    }
}
