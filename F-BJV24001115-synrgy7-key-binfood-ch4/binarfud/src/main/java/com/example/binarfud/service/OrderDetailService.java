package com.example.binarfud.service;

import com.example.binarfud.dto.OrderDetailDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface OrderDetailService {
    List<OrderDetailDTO> findAll();
    OrderDetailDTO findById(UUID id);
    OrderDetailDTO save(OrderDetailDTO orderDetailDTO);
    void deleteById(UUID id);
    Page<OrderDetailDTO> findPaginated(int page, int size);
}
