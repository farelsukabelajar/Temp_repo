package com.example.binarfud.repository;

import com.example.binarfud.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, UUID> {
}
