package com.example.binarfud.service;

import com.example.binarfud.model.Order;
import com.example.binarfud.model.OrderDetail;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Override
    public byte[] generateInvoice(UUID orderId) {
        Order order = orderService.findById(orderId);
        List<OrderDetail> orderDetails = order.getOrderDetails().stream().collect(Collectors.toList());

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, baos);
            document.open();
            document.add(new Paragraph("Invoice"));
            document.add(new Paragraph("Order ID: " + order.getOrderId()));
            document.add(new Paragraph("User: " + userService.findById(order.getUser().getUserId()).getUsername()));
            for (OrderDetail detail : orderDetails) {
                document.add(new Paragraph("Product: " + detail.getProduct().getProductName()));
                document.add(new Paragraph("Quantity: " + detail.getQuantity()));
                document.add(new Paragraph("Total Price: " + detail.getTotalPrice()));
            }
            document.close();
            return baos.toByteArray();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    @Override
    public byte[] generateReportingMerchant(UUID merchantId, Date startDate, Date endDate) {
        List<Order> orders = orderService.getOrdersByMerchantAndDateRange(merchantId, startDate, endDate);

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, baos);
            document.open();
            document.add(new Paragraph("Merchant Report"));
            document.add(new Paragraph("Merchant ID: " + merchantId));
            document.add(new Paragraph("Report Period: " + startDate + " to " + endDate));

            double totalIncome = 0;
            for (Order order : orders) {
                double orderTotal = order.getOrderDetails().stream().mapToDouble(OrderDetail::getTotalPrice).sum();
                totalIncome += orderTotal;
                document.add(new Paragraph("Order ID: " + order.getOrderId() + " - Total: " + orderTotal));
            }

            document.add(new Paragraph("Total Income: " + totalIncome));
            document.close();
            return baos.toByteArray();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }
}
