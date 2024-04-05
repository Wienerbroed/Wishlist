package com.example.wishlist.repository;

import com.example.wishlist.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
}
