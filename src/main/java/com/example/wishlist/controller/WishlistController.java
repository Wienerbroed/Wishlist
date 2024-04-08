package com.example.wishlist.controller;

import com.example.wishlist.model.Account;
import com.example.wishlist.model.Wishlist;
import com.example.wishlist.service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping("/register")
    public void registerAccount(Account register) {
        wishlistService.registerAccount(register.getUsername(), register.getPassword());
    }
    @PostMapping("/login")
    public boolean login(Account loginToAccount) {
        return wishlistService.login(loginToAccount.getUsername(), loginToAccount.getPassword());
    }

    @PostMapping("/create")
    public void createWishlist(@RequestBody Wishlist wishlist) {
        wishlistService.createWishlist(wishlist);
    }

    @GetMapping("/{id}")
    public Wishlist getWishlistById(@PathVariable int id) {
        return wishlistService.getWishlistById(id);
    }

    @PostMapping("/update")
    public void updateWishlist(@RequestBody Wishlist wishlist) {
        wishlistService.updateWishlist(wishlist);
    }

    @DeleteMapping("/{id}")
    public void deleteWishlist(@PathVariable int id) {
        wishlistService.deleteWishlist(id);
    }


}
