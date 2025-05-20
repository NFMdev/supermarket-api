package com.github.NFMdev.supermarket_api.repository;

import com.github.NFMdev.supermarket_api.model.UserList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserListRepository extends JpaRepository<UserList, Long> {
    List<UserList> findAllByUserId(Long userId);
}
