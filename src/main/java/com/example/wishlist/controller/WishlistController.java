package com.example.wishlist.controller;

import ch.qos.logback.core.model.Model;
import com.example.wishlist.model.Account;
import com.example.wishlist.service.UserService;
import com.example.wishlist.service.WishlistService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WishlistController {
    @Autowired
    public WishlistService wishlistService;

    @Autowired
    private UserService userService;



    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }
    @PostMapping("/register")
    public String registerAccount(Account account) {
        userService.saveAccount(account);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
    @PostMapping("/login")
    public String login(HttpServletRequest request,
                        @RequestParam("username") String username,
                        @RequestParam("password") String password,
                        RedirectAttributes redirectAttributes) {
        if (userService.isValidUser(username, password)) {
            String userId = userService.getUserIdByUsername(username);
            request.getSession().setAttribute("userId", userId);
            return "redirect:/wishlist";
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid login");
            return "redirect:/login";
        }
    }

    @GetMapping("/wishlist")
    public String showWishlists(HttpServletRequest request, Model model) {
        // checks if the user is logged in
        String userId = (String) request.getSession().getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }
        // load wishlist from specific user
        // not implemented yet

        return "wishlist";
    }



}
