package com.github.NFMdev.supermarket_api.controller;

import com.github.NFMdev.supermarket_api.dto.AddUserListRq;
import com.github.NFMdev.supermarket_api.dto.UserInfoDto;
import com.github.NFMdev.supermarket_api.service.JwtService;
import com.github.NFMdev.supermarket_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/info")
    public ResponseEntity<UserInfoDto> getUserInfo(@RequestParam Long userId) {
        return ResponseEntity.ok(userService.getUserInfo(userId));
    }

    @PostMapping("/add-favorite")
    public ResponseEntity<?> addFavoriteProduct(@RequestParam Long userId, @RequestParam Long productId) {
        try {
            userService.addFavoriteProduct(userId, productId);
            return ResponseEntity.ok("Product added to favorites");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error adding favorite product: " + e.getMessage());
        }
    }

    @PostMapping("/remove-favorite")
    public ResponseEntity<?> removeFavoriteProduct(@RequestParam Long userId, @RequestParam Long productId) {
        try {
            userService.removeFavoriteProduct(userId, productId);
            return ResponseEntity.ok("Product removed from favorites");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error removing favorite product: " + e.getMessage());
        }
    }

    @GetMapping("/favorites")
    public ResponseEntity<?> getUserFavorites(@RequestParam Long userId) {
        try {
            return ResponseEntity.ok(userService.getUserFavorites(userId));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error fetching favorite products: " + e.getMessage());
        }
    }

    @GetMapping("/lists")
    public ResponseEntity<?> getUserLists(@RequestParam Long userId) {
        try {
            return ResponseEntity.ok(userService.getUserLists(userId));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error fetching user lists: " + e.getMessage());
        }
    }

    @PostMapping("/add-list")
    public ResponseEntity<?> addUserList(@RequestBody AddUserListRq rq) {
        try {
            userService.addUserList(rq);
            return ResponseEntity.ok("List added successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error adding user list: " + e.getMessage());
        }
    }

    @PostMapping("/delete-list")
    public ResponseEntity<?> deleteUserList(@RequestParam Long listId) {
        try {
            userService.deleteUserList(listId);
            return ResponseEntity.ok("List deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error deleting user list: " + e.getMessage());
        }
    }
}
