package com.github.NFMdev.supermarket_api.dto;

import lombok.Data;

import java.util.List;

@Data
public class AddUserListRq {
    private String name;
    private Long userId;
    private List<Long> productIds;
}
