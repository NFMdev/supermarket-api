package com.github.NFMdev.supermarket_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDto {
    private Long id;
    private String email;
    private String username;
    private String firstname;
    private String surname;
    private String profilePic;
    private List<UserListDto> lists;
    private List<FavoriteProductDto> favoriteProducts;
}
