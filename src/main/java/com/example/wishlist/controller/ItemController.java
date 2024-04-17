package com.example.wishlist.controller;

import com.example.wishlist.model.Item;
import com.example.wishlist.service.ItemService;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;


import com.example.wishlist.model.Account;
import com.example.wishlist.model.Wishlist;
import com.example.wishlist.service.UserService;
import com.example.wishlist.service.WishlistService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
@Controller
@RequestMapping("/wishlist")
public class ItemController {



    @Autowired
    public WishlistService wishlistService;
    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;


    @PostMapping("/addItem")
    public String addItemToWishlist(@RequestParam("wishlistId") int wishlistId,
                                    @RequestParam("itemName") String itemName,
                                    @RequestParam("description") String description,
                                    @RequestParam("price") int price,
                                    RedirectAttributes redirectAttributes) {
        try {
            itemService.addItemToWishlist(wishlistId, itemName, description, price);

            redirectAttributes.addFlashAttribute("successMessage", "Item added successfully to wishlist.");

            return "redirect:/wishlist/" + wishlistId;
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/wishlist/" + wishlistId;
        }
    }

    @PostMapping("/item/delete")
    public String deleteItemFromWishlist(@RequestParam("wishlistId") int wishlistId,
                                         @RequestParam("itemName") String itemName,
                                         RedirectAttributes redirectAttributes) {
        try {
            itemService.removeItemFromWishlist(wishlistId, itemName);
            redirectAttributes.addFlashAttribute("successMessage", "Item deleted successfully from wishlist.");
            return "redirect:/wishlist/" + wishlistId;
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/wishlist/" + wishlistId;
        }
    }

    @GetMapping("/{wishlistId}/items/{itemId}/edit")
    public String showEditForm(@PathVariable int wishlistId, @PathVariable int itemId, Model model) {
        Item item = itemService.getItemById(itemId);


        if (item != null && itemService.getItemById(itemId) != null) {
            model.addAttribute("item", item);
            model.addAttribute("wishlistId", wishlistId);
            model.addAttribute("itemId", itemId);
        } else {
            return "redirect:/wishlist";
        }
        return "editItem";
    }


    @PostMapping("/{wishlistId}/items/{itemId}/edit")
    public String editItemInWishlist(@PathVariable int wishlistId,
                                     @PathVariable int itemId,
                                     @ModelAttribute Item newItem,
                                     RedirectAttributes redirectAttributes) {
        try {
            itemService.editItemInWishlist(wishlistId, itemId, newItem);
            redirectAttributes.addFlashAttribute("successMessage", "Item edited successfully");
            return "redirect:/wishlist/" + wishlistId;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to edit item: " + e.getMessage());
            return "redirect:/wishlist";
        }
    }



}
