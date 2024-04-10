package com.example.wishlist.repository;

import com.example.wishlist.model.Item;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository {

    private final JdbcTemplate jdbcTemplate;

    public ItemRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addItemToWishlist(int wishlistId, Item item) {
        String sql = "INSERT INTO wishlist_items (wishlist_id, item_name, item_description, item_price) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, wishlistId, item.getName(), item.getDescription(), item.getPrice());
    }

    public void removeItemFromWishlist(int wishlistId, int itemId) {
        String sql = "DELETE FROM wishlist_items WHERE wishlist_id = ? AND id = ?";
        jdbcTemplate.update(sql, wishlistId, itemId);
    }
    public void editItemInWishlist(int wishlistId, int itemId, Item newItem) {
        String sql = "UPDATE wishlist_items SET item_name = ?, item_description = ?, item_price = ? WHERE wishlist_id = ? AND id = ?";
        jdbcTemplate.update(sql, newItem.getName(), newItem.getDescription(), newItem.getPrice(), wishlistId, itemId);
    }

}
