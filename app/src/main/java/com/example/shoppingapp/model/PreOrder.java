package com.example.shoppingapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class PreOrder implements Serializable {

    @SerializedName("carts")
    private ArrayList<Integer> carts;

    public PreOrder(ArrayList<Integer> carts) {
        this.carts = carts;
    }

    public ArrayList<Integer> getCarts() {
        return carts;
    }

    public void setCarts(ArrayList<Integer> carts) {
        this.carts = carts;
    }
}
