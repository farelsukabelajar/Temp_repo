package com.example.binarfud.service;

import com.example.binarfud.model.OrderDetail;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface OrderDetailService {
    List<OrderDetail> findAll();
    OrderDetail findById(UUID id);
    OrderDetail save(OrderDetail orderDetail);
    void deleteById(UUID id);
    Page<OrderDetail> findPaginated(int page, int size);
}
