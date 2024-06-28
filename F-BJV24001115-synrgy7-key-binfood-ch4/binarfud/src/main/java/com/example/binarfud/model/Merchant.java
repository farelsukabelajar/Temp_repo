package com.example.binarfud.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "merchant")
public class Merchant {

    @Id
    @GeneratedValue
    private UUID merchantId;

    @Column(length = 100, nullable = false)
    private String merchantName;

    @Column(length = 255)
    private String merchantLocation;

    private boolean open;

    @OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Product> products;
}
