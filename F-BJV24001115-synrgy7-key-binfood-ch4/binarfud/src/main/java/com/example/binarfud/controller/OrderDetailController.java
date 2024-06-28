package com.example.binarfud.controller;

import com.example.binarfud.model.OrderDetail;
import com.example.binarfud.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order-details")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping
    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailService.findAll();
    }

    @GetMapping("/{id}")
    public OrderDetail getOrderDetailById(@PathVariable UUID id) {
        return orderDetailService.findById(id);
    }

    @PostMapping
    public OrderDetail createOrderDetail(@RequestBody OrderDetail orderDetail) {
        return orderDetailService.save(orderDetail);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderDetail(@PathVariable UUID id) {
        orderDetailService.deleteById(id);
    }

    @GetMapping("/paginated")
    public Page<OrderDetail> getOrderDetailsPaginated(@RequestParam int page, @RequestParam int size) {
        return orderDetailService.findPaginated(page, size);
    }
}
