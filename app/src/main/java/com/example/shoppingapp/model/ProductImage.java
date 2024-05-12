package com.example.shoppingapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductImage implements Serializable {

    @SerializedName("image")
    private String image;

    public ProductImage(String image) {
        this.image = image;
    }

    public ProductImage() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
