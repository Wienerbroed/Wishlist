package com.example.wishlist.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import com.example.wishlist.model.Item;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.util.List;

@Repository
public class ItemRepository {
    @Value("${spring.datasource.url}")
    private String db_url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    private final JdbcTemplate jdbcTemplate;
    public ItemRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addItemToWishlist(int wishlistId, Item item) {
        String sql = "INSERT INTO wishlistItems (wishlistid, itemname, description, price) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, wishlistId, item.getName(), item.getDescription(), item.getPrice());
    }
    public void removeItemFromWishlist(int wishlistId, String name) {
        String sql = "DELETE FROM WishlistItems WHERE wishlistID = ? AND itemName = ?";
        jdbcTemplate.update(sql, wishlistId, name);
    }

    public void editItemInWishlist(int wishlistId, int itemId, Item newItem) {
        String sql = "UPDATE wishlistitems SET itemname = ?, description = ?, price = ? WHERE wishlistid = ? AND id = ?";
        jdbcTemplate.update(sql, newItem.getName(), newItem.getDescription(), newItem.getPrice(), wishlistId, itemId);
    }

    public List<Item> getItemsByWishlistId(int wishlistId) {
        String sql = "SELECT * FROM wishlistItems WHERE wishlistId = ?";
        return jdbcTemplate.query(sql, new Object[]{wishlistId}, (resultSet, rowNum) ->
                new Item(resultSet.getString("itemname"),
                        resultSet.getString("description"),
                        resultSet.getInt("price"))
        );
    }
    public Item findItemByName(String itemName) {
        String query = "SELECT * FROM Items WHERE ItemName = ?";
        try (Connection connection = DriverManager.getConnection(db_url, username, password);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, itemName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Item item = new Item(resultSet.getString("ItemName"), resultSet.getString("description"), resultSet.getInt("price"));
                    return item;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public int getWishlistIdByItemName(String itemName) {
        String sql = "SELECT wishlistId FROM wishlistItems WHERE itemName = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{itemName}, Integer.class);
    }
}
