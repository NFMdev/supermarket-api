package com.github.NFMdev.supermarket_api.repository;

import com.github.NFMdev.supermarket_api.model.SupermarketProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SupermarketProductRepository extends JpaRepository<SupermarketProduct, Long> {
    List<SupermarketProduct> findBySupermarketId(Long supermarketId);
    List<SupermarketProduct> findBySupermarketIdAndSectionId(Long supermarketId, Long sectionId);
    Optional<SupermarketProduct> findBySupermarketIdAndProductId(Long supermarketId, Long productId);
}
