package com.org.binarfud.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private UUID orderId;
    private String destinationAddress;
    private LocalDate orderTime;
    private Boolean isComplete;
    private UUID userId;
}
