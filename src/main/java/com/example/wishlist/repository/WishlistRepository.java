package com.example.wishlist.repository;

import com.example.wishlist.model.Account;
import com.example.wishlist.model.Wishlist;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WishlistRepository {


    @Value("${spring.datasource.url}")
    private String db_url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;


    public WishlistRepository() {
    }

    public Wishlist addWishlist(Wishlist wishlist, int userId) {
        String insertQuery = "INSERT INTO Wishlists (UserID, WishlistName) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(db_url, username, password);
             PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setInt(1, userId);
            statement.setString(2, wishlist.getWishlistName());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new wishlist was added successfully!");
                return wishlist;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

public boolean deleteWishlist(int wishlistId, List<Integer> itemId) {
    try (Connection connection = DriverManager.getConnection(db_url, username, password)) {
        connection.setAutoCommit(false);

        if (itemId != null && !itemId.isEmpty()) {
            for (Integer item : itemId) {
                deleteItem(connection, item);
            }
        }

        String query = "DELETE FROM Wishlists WHERE WishlistID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, wishlistId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                connection.commit();
                return true;
            }
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

    private void deleteItem(Connection connection, int itemId) throws SQLException {
        String query = "DELETE FROM Items WHERE ItemID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, itemId);
            statement.executeUpdate();
        }
    }


    public boolean existsByNameAndUserId(String name, int userId) {
        String query = "SELECT COUNT(*) FROM Wishlists WHERE WishlistName = ? AND UserID = ?";
        try (Connection connection = DriverManager.getConnection(db_url, username, password);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setInt(2, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Wishlist> findByUserId(int userId) {
        List<Wishlist> wishlists = new ArrayList<>();
        String query = "SELECT * FROM Wishlists WHERE UserID = ?";
        try (Connection connection = DriverManager.getConnection(db_url, username, password);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Wishlist wishlist = new Wishlist();
                    wishlist.setWishlistId(resultSet.getInt("WishlistId")); // Set wishlistId as int
                    wishlist.setWishlistName(resultSet.getString("WishlistName"));
                    wishlists.add(wishlist);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wishlists;
    }

    public Wishlist getWishlistIdById(int wishlistId) {
        String query = "SELECT * FROM Wishlists WHERE WishlistId = ?";
        try (Connection connection = DriverManager.getConnection(db_url, username, password);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, wishlistId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Wishlist wishlist = new Wishlist();
                    wishlist.setWishlistId(resultSet.getInt("WishlistId"));
                    wishlist.setWishlistName(resultSet.getString("WishlistName"));
                    // Add other attributes as needed
                    return wishlist;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if wishlist with the given ID is not found
    }

    public boolean updateWishlistName(int wishlistId, String newWishlistName) {
        String updateQuery = "UPDATE Wishlists SET WishlistName = ? WHERE WishlistID = ?";
        try (Connection connection = DriverManager.getConnection(db_url, username, password);
             PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setString(1, newWishlistName);
            statement.setInt(2, wishlistId);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }




}
