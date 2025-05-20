package com.github.NFMdev.supermarket_api.repository;

import com.github.NFMdev.supermarket_api.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SectionRepository extends JpaRepository<Section, Long> {
    List<Section> findBySupermarketId(Long supermarketId);
    Optional<Section> findByNameAndSupermarketId(String name, Long supermarketId);
}
