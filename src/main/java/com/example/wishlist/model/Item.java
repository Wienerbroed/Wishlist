package com.example.wishlist.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.beans.ConstructorProperties;

@Entity
@Table(name = "WishlistItems")
public class Item {
    //Max length for attributes
    private static final int maxLengthName = 25;
    private static final int maxLengthDescription = 250;

    //Attributes
    private String name;
    private String description;
    private int price;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;


    // Constructor without id
    @JsonCreator
    public Item(@JsonProperty("name") String name,
                @JsonProperty("description") String description,
                @JsonProperty("price") int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Item(int itemId, String name, String description, int price) {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Item() {

    }

    //getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }
    public int getItemId() {
        return itemId;
    }
    //Setters
    public void setName(String name) {
        if (name.length()>maxLengthName){
            throw new  IllegalArgumentException("Name exceeds max characters of" + maxLengthName);
        }
        this.name = name;
    }

    public void setDescription(String description) {
        if (description.length() > maxLengthDescription){
            throw new IllegalArgumentException("Description exceeds max characters of" + maxLengthDescription);
        }
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

}
