package com.example.wishlist.service;

import com.example.wishlist.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {
    private static WishlistRepository wishlistRepo;


    @Autowired
    public WishlistService(WishlistRepository wishlistRepo) {
        this.wishlistRepo = wishlistRepo;
    }

    public static void registerAccount(String username, String password) {
        wishlistRepo.registerAccount(username, password);
    }
    public static boolean login(String username, String password) {
        return wishlistRepo.login(username, password);
    }
}
