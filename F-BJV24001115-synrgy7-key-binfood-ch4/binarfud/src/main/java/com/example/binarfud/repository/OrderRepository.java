package com.example.binarfud.repository;

import com.example.binarfud.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findByUserUserId(UUID userId);

    @Query("SELECT o FROM Order o WHERE o.merchant.merchantId = :merchantId AND o.orderTime BETWEEN :startDate AND :endDate")
    List<Order> findByMerchantAndDateRange(@Param("merchantId") UUID merchantId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
