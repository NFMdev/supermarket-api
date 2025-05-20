package com.github.NFMdev.supermarket_api.service;

import com.github.NFMdev.supermarket_api.model.Product;
import com.github.NFMdev.supermarket_api.model.SupermarketProduct;
import com.github.NFMdev.supermarket_api.repository.ProductRepository;
import com.github.NFMdev.supermarket_api.repository.SupermarketProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private final SupermarketProductRepository supermarketProductRepository;

    public ProductService(SupermarketProductRepository supermarketProductRepository) {
        this.supermarketProductRepository = supermarketProductRepository;
    }

    public SupermarketProduct addProduct(SupermarketProduct product) {
        if (supermarketProductRepository.findBySupermarketIdAndProductId(
                product.getProduct().getId(), product.getSupermarket().getId()
        ).isPresent()) {
            throw new IllegalArgumentException("Product already exists");
        }
        return supermarketProductRepository.save(product);
    }

    public SupermarketProduct updateProduct(SupermarketProduct product) {
        if (supermarketProductRepository.findById(product.getId()).isPresent()) {
            return supermarketProductRepository.save(product);
        } else {
            throw new IllegalArgumentException("Product not found");
        }
    }

    public void deleteProduct(Long id) {
        if (supermarketProductRepository.findById(id).isPresent()) {
            supermarketProductRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Product not found");
        }
    }

    public SupermarketProduct getProductById(Long id) {
        return supermarketProductRepository.findById(id).orElse(null);
    }

    public List<SupermarketProduct> getAllProductsBySupermarketId(Long id) {
        return supermarketProductRepository.findBySupermarketId(id);
    }
}
