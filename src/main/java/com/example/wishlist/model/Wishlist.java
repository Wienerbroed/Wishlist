package com.example.wishlist.model;

import java.util.List;

public class Wishlist {
    private int id;
    private String name;
    private String description;
    private int price;
    private List<String> tags;

    public Wishlist() {
    }

    public Wishlist(String name, String description, int price, List<String> tags) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description){
        this.description= description;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price){
        this.price = price;
    }

    public List<String> getTags() {
        return tags;
    }
    public void setTags(List<String> tags){
        this.tags= tags;
    }
}
