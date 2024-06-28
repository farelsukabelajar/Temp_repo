package com.example.binarfud.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;
import java.util.Set;

@Data
@NoArgsConstructor
@Setter
@Getter
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

    @OneToMany(mappedBy = "merchant")
    private Set<Product> products;
}
