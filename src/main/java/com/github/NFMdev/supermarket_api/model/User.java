package com.github.NFMdev.supermarket_api.model;

import com.github.NFMdev.supermarket_api.utils.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String email;
    private String password;
    private String username;
    private String firstname;
    private String surname;
    private String profilePic;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FavoriteProduct> favoriteProducts = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserList> lists = new ArrayList<>();
}
