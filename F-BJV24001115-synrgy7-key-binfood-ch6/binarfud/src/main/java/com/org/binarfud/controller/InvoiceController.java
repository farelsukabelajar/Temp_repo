// package com.org.binarfud.controller;

// import com.org.binarfud.service.InvoiceService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import java.time.format.DateTimeParseException;


// import java.time.LocalDate;
// import java.util.UUID;

// import org.springframework.http.MediaType; // Tambahkan impor ini

// @RestController
// @RequestMapping("/api/invoice")
// public class InvoiceController {

//     @Autowired
//     private InvoiceService invoiceService;

//     @GetMapping("/user/{userId}")
// public ResponseEntity<byte[]> generateUserInvoice(@PathVariable UUID userId) {
//     try {
//         byte[] pdfBytes = invoiceService.generateInvoice(userId);
//         HttpHeaders headers = new HttpHeaders();
//         headers.setContentType(MediaType.APPLICATION_PDF);
//         headers.setContentDispositionFormData("attachment", "invoice.pdf");
//         return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
//     } catch (IllegalArgumentException e) {
//         return ResponseEntity.badRequest().body(null);
//     } catch (Exception e) {
//         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                              .body(null);
//     }
// }

    


//     @GetMapping("/merchant/{merchantId}")
//     public ResponseEntity<Double> generateMerchantReport(@PathVariable UUID merchantId,
//                                                          @RequestParam String startDate,
//                                                          @RequestParam String endDate) {
//         try {
//             LocalDate parsedStartDate = LocalDate.parse(startDate);
//             LocalDate parsedEndDate = LocalDate.parse(endDate);
//             if (parsedStartDate.isAfter(parsedEndDate)) {
//                 return ResponseEntity.badRequest().body(null); // Ganti dengan badRequest() tanpa body
//             }
//             double revenue = invoiceService.generateReportingMerchant(merchantId, parsedStartDate, parsedEndDate);
//             return ResponseEntity.ok(revenue);
//         } catch (IllegalArgumentException e) {
//             return ResponseEntity.badRequest().body(null); // Ganti dengan badRequest() tanpa body
//         } catch (DateTimeParseException e) {
//             return ResponseEntity.badRequest().body(null); // Ganti dengan badRequest() tanpa body
//         } catch (Exception e) {
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                                  .body(null); // Ganti dengan status badRequest() tanpa body
//         }
//     }
// }
