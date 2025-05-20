package com.github.NFMdev.supermarket_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteProductDto {
    private Long id;
    private Long productId;
    private String productName;
    private String productBrand;
    private String productCategory;
}

