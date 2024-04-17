package com.example.wishlist.service;

import com.example.wishlist.model.Item;
import com.example.wishlist.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public int addItemToWishlist(int wishlistId, String itemName, String description, int price) {
        Item newItem = new Item(itemName, description, price);

        int itemId = itemRepository.addItemToWishlist(wishlistId, newItem);
        return itemId;
    }

    public List<Item> getItemsByWishlistId(int wishlistId) {
        return itemRepository.getItemsByWishlistId(wishlistId);
    }

    public Item findItemByName(String name) {
        return itemRepository.findItemByName(name);
    }
    public int getWishlistIdByItemName(String name) {
        return itemRepository.getWishlistIdByItemName(name);
    }

    public void removeItemFromWishlist(int wishlistId, String name) {
        itemRepository.removeItemFromWishlist(wishlistId, name);
    }


    public void editItemInWishlist(int wishlistId, int itemId, Item newItem) {
        itemRepository.editItemInWishlist(wishlistId, itemId, newItem);
    }

    public Item getItemById(int itemId) {
        Item item = itemRepository.getItemById(itemId);
        return item;
    }



}