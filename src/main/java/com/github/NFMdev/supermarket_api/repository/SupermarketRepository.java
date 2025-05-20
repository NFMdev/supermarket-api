package com.github.NFMdev.supermarket_api.repository;

import com.github.NFMdev.supermarket_api.model.Supermarket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SupermarketRepository extends JpaRepository<Supermarket, Long> {
    Optional<Supermarket> findByName(String name);
    List<Supermarket> findAllByAddress(String address);
}
