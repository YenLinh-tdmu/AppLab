package com.example.applab;

public class Product {
    private String name;
    private int imageResId;

    public Product(String name, int imageResId) {
        this.name = name;
        this.imageResId = imageResId;
    }

    // Getter
    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }
}
