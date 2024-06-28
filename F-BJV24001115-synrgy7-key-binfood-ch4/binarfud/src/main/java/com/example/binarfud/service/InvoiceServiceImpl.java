package com.example.binarfud.service;

import com.example.binarfud.dto.OrderDTO;
import com.example.binarfud.dto.OrderDetailDTO;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private static final Logger logger = LoggerFactory.getLogger(InvoiceServiceImpl.class);

    private final OrderService orderService;
    private final UserService userService;
    private final MerchantService merchantService;
    private final ProductService productService;

    @Autowired
    public InvoiceServiceImpl(OrderService orderService, UserService userService, MerchantService merchantService, ProductService productService) {
        this.orderService = orderService;
        this.userService = userService;
        this.merchantService = merchantService;
        this.productService = productService;
    }

    @Override
    public byte[] generateInvoice(UUID orderId) {
        if (orderId == null) {
            logger.error("Order ID cannot be null");
            throw new IllegalArgumentException("Order ID cannot be null");
        }

        OrderDTO orderDTO = orderService.findById(orderId);
        if (orderDTO == null) {
            logger.error("Order not found for ID: {}", orderId);
            throw new IllegalArgumentException("Order not found for ID: " + orderId);
        }

        List<OrderDetailDTO> orderDetails = orderDTO.getOrderDetails();

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, baos);
            document.open();
            document.add(new Paragraph("Invoice"));
            document.add(new Paragraph("Order ID: " + orderDTO.getOrderId()));
            document.add(new Paragraph("User: " + userService.findById(orderDTO.getUserId()).getUsername()));
            for (OrderDetailDTO detail : orderDetails) {
                document.add(new Paragraph("Product: " + productService.findById(detail.getProductId()).getProductName()));
                document.add(new Paragraph("Quantity: " + detail.getQuantity()));
                document.add(new Paragraph("Total Price: " + detail.getTotalPrice()));
            }
            document.close();
            return baos.toByteArray();
        } catch (DocumentException | IOException e) {
            logger.error("Error generating invoice for order ID: {}", orderId, e);
            return new byte[0];
        }
    }

    @Override
    public byte[] generateReportingMerchant(UUID merchantId, Date startDate, Date endDate) {
        if (merchantId == null) {
            logger.error("Merchant ID cannot be null");
            throw new IllegalArgumentException("Merchant ID cannot be null");
        }

        if (startDate == null || endDate == null) {
            logger.error("Start date and end date cannot be null");
            throw new IllegalArgumentException("Start date and end date cannot be null");
        }

        List<OrderDTO> orders = orderService.getOrdersByMerchantAndDateRange(merchantId, startDate, endDate);

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, baos);
            document.open();
            document.add(new Paragraph("Merchant Report"));
            document.add(new Paragraph("Merchant ID: " + merchantId));
            document.add(new Paragraph("Report Period: " + startDate + " to " + endDate));

            double totalIncome = 0;
            for (OrderDTO order : orders) {
                double orderTotal = order.getOrderDetails().stream().mapToDouble(OrderDetailDTO::getTotalPrice).sum();
                totalIncome += orderTotal;
                document.add(new Paragraph("Order ID: " + order.getOrderId() + " - Total: " + orderTotal));
            }

            document.add(new Paragraph("Total Income: " + totalIncome));
            document.close();
            return baos.toByteArray();
        } catch (DocumentException | IOException e) {
            logger.error("Error generating report for merchant ID: {}", merchantId, e);
            return new byte[0];
        }
    }
}
