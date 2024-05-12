package com.example.shoppingapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Subproduct implements Serializable {

    @SerializedName("id")
    private int id;
    @SerializedName("subproduct")
    private int  subproduct;
    @SerializedName("title")
    private String title;

    @SerializedName("is_active")
    private boolean isActive;

    public Subproduct(int id, String title, boolean isActive) {
        this.id = id;
        this.title = title;
        this.isActive = isActive;
    }

    public Subproduct() {
    }

    public int getSubproduct() {
        return subproduct;
    }

    public void setSubproduct(int subproduct) {
        this.subproduct = subproduct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
