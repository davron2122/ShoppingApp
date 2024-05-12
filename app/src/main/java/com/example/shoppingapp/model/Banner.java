package com.example.shoppingapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Banner implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("image")
    private String image;

    @SerializedName("is_active")
    private boolean isActive;
    @SerializedName("date_created")
    private String dateCreated;

    public Banner() {

    }

    public Banner(int id, String image, boolean isActive, String dateCreated) {
        this.id = id;
        this.image = image;
        this.isActive = isActive;
        this.dateCreated = dateCreated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
}
