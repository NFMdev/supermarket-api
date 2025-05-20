package com.github.NFMdev.supermarket_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class ListItem {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private UserList userList;

    @ManyToOne
    private SupermarketProduct product;
}
