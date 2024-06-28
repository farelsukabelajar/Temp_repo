package com.example.binarfud.controller;

import com.example.binarfud.dto.OrderDTO;
import com.example.binarfud.service.InvoiceService;
import com.example.binarfud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/details/{userId}")
    public ResponseEntity<List<OrderDTO>> getOrderDetailsByUser(@PathVariable UUID userId) {
        List<OrderDTO> orders = orderService.getOrderDetailsByUser(userId);
        if (orders == null || orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping("/invoice/{orderId}")
    public ResponseEntity<byte[]> generateInvoice(@PathVariable UUID orderId) {
        byte[] pdfData = invoiceService.generateInvoice(orderId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=invoice.pdf");

        return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
    }

    @PostMapping("/report")
    public ResponseEntity<byte[]> generateReportingMerchant(@RequestParam UUID merchantId, @RequestParam String startDate, @RequestParam String endDate) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date start = formatter.parse(startDate);
            Date end = formatter.parse(endDate);
            byte[] pdfData = invoiceService.generateReportingMerchant(merchantId, start, end);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=report.pdf");

            return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
        } catch (ParseException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
