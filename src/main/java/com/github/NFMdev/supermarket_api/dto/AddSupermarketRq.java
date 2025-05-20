package com.github.NFMdev.supermarket_api.dto;

import lombok.Data;

@Data
public class AddSupermarketRq {
    private String name;
    private String address;
    private String location;
}
