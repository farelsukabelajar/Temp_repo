package com.example.binarfud.service;

import com.example.binarfud.model.OrderDetail;
import com.example.binarfud.repository.OrderDetailRepository;
import com.example.binarfud.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private static final Logger logger = LoggerFactory.getLogger(OrderDetailServiceImpl.class);

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderDetail> findAll() {
        logger.info("Fetching all order details");
        return orderDetailRepository.findAll();
    }

    @Override
    public OrderDetail findById(UUID id) {
        logger.debug("Fetching order detail with id: {}", id);
        return orderDetailRepository.findById(id).orElse(null);
    }

    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        logger.info("Saving order detail with id: {}", orderDetail.getOrderDetailId());
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public void deleteById(UUID id) {
        logger.warn("Deleting order detail with id: {}", id);
        orderDetailRepository.deleteById(id);
    }

    @Override
    public Page<OrderDetail> findPaginated(int page, int size) {
        logger.debug("Fetching order details with pagination - page: {}, size: {}", page, size);
        return orderDetailRepository.findAll(PageRequest.of(page, size));
    }
}
