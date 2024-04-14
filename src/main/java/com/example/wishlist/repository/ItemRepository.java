package com.example.wishlist.repository;

import org.springframework.stereotype.Repository;
import com.example.wishlist.model.Item;
import org.springframework.jdbc.core.JdbcTemplate;
@Repository
public class ItemRepository {

    private final JdbcTemplate jdbcTemplate;
    public ItemRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addItemToWishlist(int wishlistId, Item item) {
        String sql = "INSERT INTO wishlistItems (wishlistid, itemname, description, price) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, wishlistId, item.getName(), item.getDescription(), item.getPrice());
    }
    public void removeItemFromWishlist(int wishlistId, int itemId) {
        String sql = "DELETE FROM WishlistItems WHERE wishlistID = ? AND id = ?";
        jdbcTemplate.update(sql, wishlistId, itemId);
    }

    public void editItemInWishlist(int wishlistId, int itemId, Item newItem) {
        String sql = "UPDATE wishlistitems SET itemname = ?, description = ?, price = ? WHERE wishlistid = ? AND id = ?";
        jdbcTemplate.update(sql, newItem.getName(), newItem.getDescription(), newItem.getPrice(), wishlistId, itemId);
    }
}
