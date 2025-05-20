package com.github.NFMdev.supermarket_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class SupermarketProduct {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Supermarket supermarket;

    @ManyToOne
    private Section section;

    @ManyToOne
    private Product product;

    private BigDecimal price;
    private Integer quantity;
}
