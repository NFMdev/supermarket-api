package com.github.NFMdev.supermarket_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteProduct {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;

    public FavoriteProduct(Long userId, Long productId) {
        this.user = new User();
        this.user.setId(userId);
        this.product = new Product();
        this.product.setId(productId);
    }
}
