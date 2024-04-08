package com.example.wishlist.repository;

import com.example.wishlist.model.Account;
import com.example.wishlist.model.Wishlist;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class WishlistRepository {
    public WishlistRepository() {
    }

    private JdbcTemplate jdbcTemplate;

    public void registerAccount(String username, String password) {
        String sql = "INSERT INTO accounts (username, password) VALUES (?, ?)";
        jdbcTemplate.update(sql, username, password);
    }
    public boolean login(String username, String password) {
        String sql = "SELECT * FROM accounts WHERE username = ? AND password = ?";
        List<Account> accounts = jdbcTemplate.query(sql, new Object[]{username, password}, (rs, rowNum) -> {
            Account account = new Account();
            account.setUsername(rs.getString("username"));
            account.setPassword(rs.getString("password"));
            return account;
        });

        return !accounts.isEmpty();
    }

    public void createWishlist(Wishlist wishlist) {
        String sql = "INSERT INTO wishlists (name, description, price, tags) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, wishlist.getName(), wishlist.getDescription(), wishlist.getPrice(), wishlist.getTags());
    }

    public Wishlist getWishlistById(int id) {
        String sql = "SELECT * FROM wishlists WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            Wishlist wishlist = new Wishlist();
            wishlist.setId(rs.getInt("id"));
            wishlist.setName(rs.getString("name"));
            wishlist.setDescription(rs.getString("description"));
            wishlist.setPrice(rs.getInt("price"));
            String tagsString = rs.getString("tags");
            if (tagsString != null && !tagsString.isEmpty()) {
                List<String> tags = Arrays.asList(tagsString.split(","));
                wishlist.setTags(tags);
            }
            return wishlist;
        });
    }

    public void updateWishlist(Wishlist wishlist) {
        String sql = "UPDATE wishlists SET name = ?, description = ?, price = ?, tags = ? WHERE id = ?";
        jdbcTemplate.update(sql, wishlist.getName(), wishlist.getDescription(), wishlist.getPrice(), wishlist.getTags(), wishlist.getId());
    }

    public void deleteWishlist(int id) {
        String sql = "DELETE FROM wishlists WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

}
