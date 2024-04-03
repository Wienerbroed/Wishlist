package com.example.wishlist.model;




public class Item {
    //Max length for attributes
    private static final int maxLengthName = 25;
    private static final int maxLengthDescription = 250;

    //Attributes
    private String name;
    private String description;
    private int price;

    public Item(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    //getter
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
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
}
