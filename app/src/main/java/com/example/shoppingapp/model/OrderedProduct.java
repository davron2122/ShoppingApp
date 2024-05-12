package com.example.shoppingapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OrderedProduct implements Serializable {

    @SerializedName("product")
    private Product product;

    @SerializedName("option")
    private Option option;

    @SerializedName("quantity")
    private int quantity;


    @SerializedName("price_current")
    private int priceCurrent;


    @SerializedName("price_original")
    private int priceOriginal;

    public int getPriceCurrent() {
        return priceCurrent;
    }

    public void setPriceCurrent(int priceCurrent) {
        this.priceCurrent = priceCurrent;
    }

    public int getPriceOriginal() {
        return priceOriginal;
    }

    public void setPriceOriginal(int priceOriginal) {
        this.priceOriginal = priceOriginal;
    }

    @Expose
    private boolean selected = false;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public OrderedProduct(Product product, Option option, int quantity) {
        this.product = product;
        this.option = option;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
