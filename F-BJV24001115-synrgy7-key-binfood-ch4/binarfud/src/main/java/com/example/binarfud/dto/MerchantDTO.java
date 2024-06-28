package com.example.binarfud.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class MerchantDTO {
    private UUID merchantId;
    private String merchantName;
    private String merchantLocation;
    private boolean open;
}
