package com.example.wishlist.controller;

import com.example.wishlist.model.Account;
import com.example.wishlist.service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WishlistController {
    public WishlistService wishlistService;


    @PostMapping("/register")
    public void registerAccount(Account register) {
        wishlistService.registerAccount(register.getUsername(), register.getPassword());
    }
    @PostMapping("/login")
    public boolean login(Account loginToAccount) {
        return wishlistService.login(loginToAccount.getUsername(), loginToAccount.getPassword());
    }
}
