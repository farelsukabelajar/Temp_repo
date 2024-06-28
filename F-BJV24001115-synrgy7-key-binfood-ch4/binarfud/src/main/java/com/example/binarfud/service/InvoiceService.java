package com.example.binarfud.service;

import java.util.UUID;
import java.util.Date;

public interface InvoiceService {
    byte[] generateInvoice(UUID orderId);
    byte[] generateReportingMerchant(UUID merchantId, Date startDate, Date endDate);
}
