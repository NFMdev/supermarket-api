package com.github.NFMdev.supermarket_api.mapper;

import com.github.NFMdev.supermarket_api.dto.FavoriteProductDto;
import com.github.NFMdev.supermarket_api.dto.UserInfoDto;
import com.github.NFMdev.supermarket_api.dto.UserListDto;
import com.github.NFMdev.supermarket_api.model.User;
import com.github.NFMdev.supermarket_api.repository.FavoriteProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;
    private final FavoriteProductRepository favoriteProductRepository;

    public UserMapper(FavoriteProductRepository favoriteProductRepository) {
        this.favoriteProductRepository = favoriteProductRepository;
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration().setSkipNullEnabled(true);
    }

    public UserInfoDto toDto(User user) {
        UserInfoDto dto = modelMapper.map(user, UserInfoDto.class);

        dto.setLists(user.getLists().stream()
                .map(list -> modelMapper.map(list, UserListDto.class))
                .collect(Collectors.toList()));

        dto.setFavoriteProducts(favoriteProductRepository.findAllByUserId(user.getId()).stream()
                .map(fav -> {
                    FavoriteProductDto favDto = new FavoriteProductDto();
                    favDto.setId(fav.getId());
                    favDto.setProductId(fav.getProduct().getId());
                    favDto.setProductName(fav.getProduct().getName());
                    favDto.setProductBrand(fav.getProduct().getBrand());
                    favDto.setProductCategory(fav.getProduct().getCategory());
                    return favDto;
                })
                .collect(Collectors.toList()));

        return dto;
    }
}

