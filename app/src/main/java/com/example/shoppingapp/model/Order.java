package com.example.shoppingapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {

    @SerializedName("id")
    private Integer id;

    @SerializedName("order_number")
    private String orderNumber;
    @SerializedName("status")
    private String orderStatus;
    @SerializedName("total_current_price")
    private Integer totalCurrentPrice;
    @SerializedName("total_original_price")
    private Integer totalOriginalPrice;
    @SerializedName("payment")
    private String payment;



    @SerializedName("products")
    private ArrayList<OrderedProduct> products;




    @SerializedName("name")
    private String name;


    @SerializedName("phone")
    private String phone;


    @SerializedName("address")
    private String address;

    public Order() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public ArrayList<OrderedProduct> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<OrderedProduct> products) {
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getTotalCurrentPrice() {
        return totalCurrentPrice;
    }

    public void setTotalCurrentPrice(Integer totalCurrentPrice) {
        this.totalCurrentPrice = totalCurrentPrice;
    }

    public Integer getTotalOriginalPrice() {
        return totalOriginalPrice;
    }

    public void setTotalOriginalPrice(Integer totalOriginalPrice) {
        this.totalOriginalPrice = totalOriginalPrice;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderNumber='" + orderNumber + '\'' +
                ", products=" + products +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
