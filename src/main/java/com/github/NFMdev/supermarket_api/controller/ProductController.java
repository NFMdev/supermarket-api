package com.github.NFMdev.supermarket_api.controller;

import com.github.NFMdev.supermarket_api.model.SupermarketProduct;
import com.github.NFMdev.supermarket_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody SupermarketProduct product) {
        try {
            SupermarketProduct response = productService.addProduct(product);
            if (response == null) {
                return ResponseEntity.badRequest().body("Product already exists");
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error adding product: " + e.getMessage());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody SupermarketProduct product) {
        try {
            SupermarketProduct response = productService.updateProduct(product);
            if (response == null) {
                return ResponseEntity.badRequest().body("Product not found");
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error updating product: " + e.getMessage());
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteProduct(@RequestBody Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.ok("Product deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error deleting product: " + e.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        try {
            SupermarketProduct product = productService.getProductById(id);
            if (product != null) {
                return ResponseEntity.ok(product);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error fetching product: " + e.getMessage());
        }
    }

    @GetMapping("/getAll/{id}")
    public ResponseEntity<?> getAllProductsBySupermarketId(@PathVariable Long id) {
        try {
            List<SupermarketProduct> products = productService.getAllProductsBySupermarketId(id);
            if (products != null && !products.isEmpty()) {
                return ResponseEntity.ok(products);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error fetching products: " + e.getMessage());
        }
    }
}
