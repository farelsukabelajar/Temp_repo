package com.org.binarfud.model;

import java.util.UUID;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import lombok.*;
import jakarta.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "product")
@SQLDelete(sql = "update product set deleted = true where id =?")
@SQLRestriction("deleted = false")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "product_id", columnDefinition = "uuid", updatable = false)
    private UUID id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "price")
    private double price;

    @ManyToOne(targetEntity = Merchant.class)
    @JoinColumn(name = "merchant_id", nullable = false)
    private Merchant merchant;
}
