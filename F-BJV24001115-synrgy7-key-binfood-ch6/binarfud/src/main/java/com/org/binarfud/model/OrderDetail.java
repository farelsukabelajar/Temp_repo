package com.org.binarfud.model;

import java.util.UUID;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_detail")
@SQLDelete(sql = "update order_detail set deleted = true where id =?")
@SQLRestriction("deleted = false")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "order_detail_id", columnDefinition = "uuid", updatable = false)
    private UUID id;

    @Column(name = "quantity")
    private int qty;

    @Column(name = "total_price")
    private double price;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(targetEntity = Order.class)
    @JoinColumn(name = "order_id")
    private Order order;

    public void addQty(int qty) {
        this.qty += qty;
    }
}
