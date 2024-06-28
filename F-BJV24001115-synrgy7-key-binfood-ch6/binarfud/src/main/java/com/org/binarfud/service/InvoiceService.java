// package com.org.binarfud.service;

// import com.org.binarfud.dto.OrderDTO;
// import com.org.binarfud.dto.UserDTO;
// import com.org.binarfud.repo.MerchantRepo;
// import com.org.binarfud.repo.OrderRepo;
// import com.org.binarfud.repo.UserRepo;
// import net.sf.jasperreports.engine.*;
// import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// import java.io.ByteArrayOutputStream;
// import java.io.InputStream;
// import java.time.LocalDate;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.UUID;

// @Service
// public class InvoiceService {

//     @Autowired
//     private OrderService orderService;

//     @Autowired
//     private UserService userService;

//     public byte[] generateInvoice(UUID userId) {
//         UserDTO user = userService.getUserById(userId)
//                                   .orElseThrow(() -> new IllegalArgumentException("User not found"));
//         List<OrderDTO> orders = orderService.getOrderDetailsByUser(userId);

//         try {
//             InputStream reportStream = getClass().getResourceAsStream("/reports/invoice.jrxml");
//             JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

//             JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(orders);

//             Map<String, Object> parameters = new HashMap<>();
//             parameters.put("Username", user.getUsername());
//             parameters.put("Email", user.getEmailAddress());

//             JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

//             ByteArrayOutputStream baos = new ByteArrayOutputStream();
//             JasperExportManager.exportReportToPdfStream(jasperPrint, baos);

//             return baos.toByteArray();
//         } catch (JRException e) {
//             throw new RuntimeException("Error generating PDF", e);
//         }
//     }

//     @Transactional
//     public double generateReportingMerchant(UUID merchantId, LocalDate startDate, LocalDate endDate) {
//         Double revenue = merchantRepo.findRevenueByMerchantAndPeriod(merchantId, startDate, endDate);
//         return revenue != null ? revenue : 0.0;
//     }
// }
