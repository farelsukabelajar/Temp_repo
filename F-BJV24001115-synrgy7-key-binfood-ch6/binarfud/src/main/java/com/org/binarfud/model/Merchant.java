package com.org.binarfud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "merchant")
@SQLDelete(sql = "update merchant set deleted = true where id =?")
@SQLRestriction("deleted = false")
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "merchant_id", columnDefinition = "uuid", updatable = false)
    private UUID id;

    @Column(name = "merchant_name")
    private String name;

    @Column(name = "merchant_location")
    private String location;

    @Column(name = "open")
    private boolean isOpen;

    @JsonIgnore
    @OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL)
    private List<Product> products;
}
