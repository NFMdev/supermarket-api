package com.github.NFMdev.supermarket_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Supermarket {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String address;
    private String location;
}
