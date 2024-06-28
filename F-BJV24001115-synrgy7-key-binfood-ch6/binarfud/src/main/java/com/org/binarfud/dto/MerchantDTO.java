package com.org.binarfud.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MerchantDTO {
    private UUID id;
    private String name;
    private String location;
    private boolean isOpen;
}
