package com.github.NFMdev.supermarket_api.service;

import com.github.NFMdev.supermarket_api.dto.AddUserListRq;
import com.github.NFMdev.supermarket_api.dto.UserInfoDto;
import com.github.NFMdev.supermarket_api.mapper.UserMapper;
import com.github.NFMdev.supermarket_api.model.*;
import com.github.NFMdev.supermarket_api.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final FavoriteProductRepository favoriteProductRepository;
    private final UserListRepository userListRepository;
    private final SupermarketProductRepository supermarketProductRepository;
    private final ListItemRepository listItemRepository;
    private final UserMapper userMapper;

    public UserService(
            UserRepository userRepository,
            FavoriteProductRepository favoriteProductRepository,
            UserListRepository userListRepository,
            SupermarketProductRepository supermarketProductRepository,
            ListItemRepository listItemRepository,
            UserMapper userMapper
    ) {
        this.userRepository = userRepository;
        this.favoriteProductRepository = favoriteProductRepository;
        this.userListRepository = userListRepository;
        this.supermarketProductRepository = supermarketProductRepository;
        this.listItemRepository = listItemRepository;
        this.userMapper = userMapper;
    }

    public UserInfoDto getUserInfo(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return userMapper.toDto(user);
    }

    public List<FavoriteProduct> getUserFavorites(Long userId) {
        return favoriteProductRepository.findAllByUserId(userId);
    }

    public void addFavoriteProduct(Long userId, Long productId) {
        if (favoriteProductRepository.findByUserIdAndProductId(userId, productId).isEmpty()) {
            favoriteProductRepository.save(new FavoriteProduct(userId, productId));
        }
    }

    public void removeFavoriteProduct(Long userId, Long productId) {
        favoriteProductRepository.deleteByUserIdAndProductId(userId, productId);
    }

    public List<UserList> getUserLists(Long userId) {
        return userListRepository.findAllByUserId(userId);
    }

    public void addUserList(AddUserListRq rq) {
        User user = userRepository.findById(rq.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        UserList userList = new UserList();
        userList.setName(rq.getName());
        userList.setUser(user);
        userListRepository.save(userList);
        rq.getProductIds().forEach(productId -> {
            ListItem listItem = new ListItem();
            SupermarketProduct product = supermarketProductRepository.findById(productId)
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));
            listItem.setProduct(product);
            listItem.setUserList(userList);
            listItemRepository.save(listItem);
        });
    }

    public void deleteUserList(Long listId) {
        UserList userList = userListRepository.findById(listId)
                .orElseThrow(() -> new IllegalArgumentException("User list not found"));
        userListRepository.delete(userList);
    }
}
