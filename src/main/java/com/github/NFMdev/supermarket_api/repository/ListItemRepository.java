package com.github.NFMdev.supermarket_api.repository;

import com.github.NFMdev.supermarket_api.model.ListItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListItemRepository extends JpaRepository<ListItem, Long> {
    List<ListItem> findAllByUserListId(Long userListId);
    void deleteByUserListId(Long userListId);
}
