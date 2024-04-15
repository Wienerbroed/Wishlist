package com.example.wishlist.service;

import com.example.wishlist.model.Item;
import com.example.wishlist.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void addItemToWishlist(int wishlistId, String itemName, String description, int price) {
        // Create a new item
        Item newItem = new Item(itemName, description, price);

        // Call the repository method to add the item to the wishlist
        itemRepository.addItemToWishlist(wishlistId, newItem);
    }
}