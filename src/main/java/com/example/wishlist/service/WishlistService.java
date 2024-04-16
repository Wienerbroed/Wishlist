package com.example.wishlist.service;

import com.example.wishlist.model.Wishlist;
import com.example.wishlist.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistService {
    private final WishlistRepository wishlistRepository;
    private  final UserService userService;

    @Autowired
    public WishlistService(WishlistRepository wishlistRepository,UserService userService) {
        this.wishlistRepository = wishlistRepository;
        this.userService = userService;
    }

    public Wishlist addWishlist(Wishlist wishlist, String userIdString) {
        if (userIdString != null && !userIdString.isEmpty()) {
            int userId = Integer.parseInt(userIdString);

            if (wishlist.getWishlistName() == null || wishlist.getWishlistName().isEmpty() || wishlist.getWishlistName().length() > 100) {
                throw new IllegalArgumentException("Wishlist name must be between 1 and 100 characters.");
            }

            if (wishlistRepository.existsByNameAndUserId(wishlist.getWishlistName(), userId)) {
                throw new IllegalStateException("Wishlist with the same name already exists for this user.");
            }

            return wishlistRepository.addWishlist(wishlist, userId);
        } else {
            throw new IllegalArgumentException("User ID is null or empty.");
        }
    }

    public List<Wishlist> getWishlistsByUserId(int userId) {
        List<Wishlist> wishlists = wishlistRepository.findByUserId(userId);
        return wishlists;
    }


    public Wishlist getWishlistById(int wishlistId) {
        return wishlistRepository.getWishlistIdById(wishlistId);
    }

    public boolean deleteWishlist(int wishlistId,  List<Integer> itemId) {
       return wishlistRepository.deleteWishlist(wishlistId, itemId);
    }




}
