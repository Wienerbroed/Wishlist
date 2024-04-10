package com.example.wishlist.service;

import com.example.wishlist.model.Item;
import com.example.wishlist.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    private static ItemRepository itemRepository;
    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public static void addItemToWishlist(int wishlistId, Item item) {
        itemRepository.addItemToWishlist(wishlistId, item);
    }

    public static void removeItemFromWishlist(int wishlistId, int itemId) {
        itemRepository.removeItemFromWishlist(wishlistId, itemId);
    }

    public static void editItemInWishlist(int wishlistId, int itemId, Item newItem) {
        itemRepository.editItemInWishlist(wishlistId, itemId, newItem);
    }


}
