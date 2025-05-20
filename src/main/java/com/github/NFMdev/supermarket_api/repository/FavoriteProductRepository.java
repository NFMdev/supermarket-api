package com.github.NFMdev.supermarket_api.repository;

import com.github.NFMdev.supermarket_api.model.FavoriteProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteProductRepository extends JpaRepository<FavoriteProduct, Long> {
    List<FavoriteProduct> findAllByUserId(Long userId);
    Optional<FavoriteProduct> findByUserIdAndProductId(Long userId, Long productId);
    void deleteByUserIdAndProductId(Long userId, Long productId);
}
