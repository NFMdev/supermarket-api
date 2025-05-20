package com.github.NFMdev.supermarket_api.repository;

import com.github.NFMdev.supermarket_api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByNameContainingIgnoreCase(String name);
    List<Product> findAllByCategory(String category);

}
