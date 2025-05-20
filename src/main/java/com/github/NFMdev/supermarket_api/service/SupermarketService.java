package com.github.NFMdev.supermarket_api.service;

import com.github.NFMdev.supermarket_api.dto.AddSupermarketRq;
import com.github.NFMdev.supermarket_api.model.Supermarket;
import com.github.NFMdev.supermarket_api.repository.SupermarketRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupermarketService {
    @Autowired
    private final SupermarketRepository supermarketRepository;

    @Autowired
    private ModelMapper modelMapper;

    public SupermarketService(SupermarketRepository supermarketRepository) {
        this.supermarketRepository = supermarketRepository;
    }

    public Supermarket addSupermarket(AddSupermarketRq rq) throws IllegalArgumentException {
        Supermarket supermarket = modelMapper.map(rq, Supermarket.class);
        if (supermarketRepository.findByName(supermarket.getName()).isPresent()) {
            throw new IllegalArgumentException("Supermarket already exists");
        }
        return supermarketRepository.save(supermarket);
    }

    public Supermarket getSupermarketById(Long id) {
        return supermarketRepository.findById(id).orElse(null);
    }

    public Supermarket updateSupermarket(Supermarket supermarket) {
        if (supermarketRepository.findById(supermarket.getId()).isPresent()) {
            return supermarketRepository.save(supermarket);
        } else {
            throw new IllegalArgumentException("Supermarket not found");
        }
    }

    public void deleteSupermarket(Long id) {
        if (supermarketRepository.findById(id).isPresent()) {
            supermarketRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Supermarket not found");
        }
    }

    public List<Supermarket> getAllSupermarkets() {
        return supermarketRepository.findAll();
    }
}
