package com.github.NFMdev.supermarket_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class UserList {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    @JsonIgnore
    private User user;
}
