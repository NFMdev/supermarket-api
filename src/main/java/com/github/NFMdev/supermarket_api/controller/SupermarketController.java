package com.github.NFMdev.supermarket_api.controller;

import com.github.NFMdev.supermarket_api.dto.AddSupermarketRq;
import com.github.NFMdev.supermarket_api.model.Supermarket;
import com.github.NFMdev.supermarket_api.service.SupermarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supermarket")
public class SupermarketController {
    @Autowired
    private SupermarketService supermarketService;

    @PostMapping("/add")
    public ResponseEntity<?> addSupermarket(@RequestBody AddSupermarketRq rq) {
        try {
            Supermarket supermarket = supermarketService.addSupermarket(rq);
            return ResponseEntity.ok(supermarket);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error adding supermarket: " + e.getMessage());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateSupermarket(@RequestBody Supermarket rq) {
        try {
            Supermarket supermarket = supermarketService.updateSupermarket(rq);
            return ResponseEntity.ok(supermarket);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error updating supermarket: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSupermarket(@PathVariable Long id) {
        try {
            supermarketService.deleteSupermarket(id);
            return ResponseEntity.ok("Supermarket deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error deleting supermarket: " + e.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getSupermarketById(@PathVariable Long id) {
        try {
            Supermarket supermarket = supermarketService.getSupermarketById(id);
            if (supermarket != null) {
                return ResponseEntity.ok(supermarket);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error fetching supermarket: " + e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllSupermarkets() {
        try {
            List<Supermarket> supermarkets = supermarketService.getAllSupermarkets();
            if (supermarkets != null && !supermarkets.isEmpty()) {
                return ResponseEntity.ok(supermarkets);
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error fetching supermarkets: " + e.getMessage());
        }
    }

}
