package com.github.NFMdev.supermarket_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Section {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String location;

    @ManyToOne
    private Supermarket supermarket;
}
