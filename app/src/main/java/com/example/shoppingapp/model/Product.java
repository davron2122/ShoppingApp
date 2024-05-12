
package com.example.shoppingapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Product implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("subproduct")
    @Expose
    private int subproduct;
    @SerializedName("price_current")
    @Expose
    private int priceCurrent;
    @SerializedName("price_original")
    @Expose
    private int priceOriginal;
    @SerializedName("quantity")
    @Expose
    private int quantity;
    @SerializedName("infinite")
    @Expose
    private boolean infinite;
    @SerializedName("has_option")
    @Expose
    private boolean hasOption;
    @SerializedName("image")
    @Expose
    private ProductImage image;

    @SerializedName("images")
    @Expose
    private ArrayList<ProductImage> images;

    @SerializedName("color_options")
    @Expose
    private ArrayList<ColorOption> colorOptions;

    @SerializedName("size_options")
    @Expose
    private ArrayList<SizeOption> sizeOptions;


    @SerializedName("options")
    @Expose
    private ArrayList<Option> options;
    @SerializedName("is_active")
    @Expose
    private boolean isActive;
    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("made_in")
    @Expose
    private String madeIn;

    /**
     * No args constructor for use in serialization
     */
    public Product() {
    }

    /**
     * @param image
     * @param quantity
     * @param infinite
     * @param description
     * @param subproduct
     * @param title
     * @param isActive
     * @param priceCurrent
     * @param hasOption
     * @param priceOriginal
     * @param id
     * @param madeIn
     * @param brand
     */
    public Product(int id, String title, String description, int subproduct, int priceCurrent, int priceOriginal, int quantity, boolean infinite, boolean hasOption, ProductImage image, boolean isActive, String brand, String madeIn) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.subproduct = subproduct;
        this.priceCurrent = priceCurrent;
        this.priceOriginal = priceOriginal;
        this.quantity = quantity;
        this.infinite = infinite;
        this.hasOption = hasOption;
        this.image = image;
        this.isActive = isActive;
        this.brand = brand;
        this.madeIn = madeIn;
    }

    public ArrayList<ColorOption> getColorOptions() {
        return colorOptions;
    }

    public void setColorOptions(ArrayList<ColorOption> colorOptions) {
        this.colorOptions = colorOptions;
    }

    public ArrayList<SizeOption> getSizeOptions() {
        return sizeOptions;
    }

    public void setSizeOptions(ArrayList<SizeOption> sizeOptions) {
        this.sizeOptions = sizeOptions;
    }

    public ArrayList<ProductImage> getImages() {
        return images;
    }

    public void setImages(ArrayList<ProductImage> images) {
        this.images = images;
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

    public String getDescription() {
        return description;
    }

    public ArrayList<Option> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<Option> options) {
        this.options = options;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSubproduct() {
        return subproduct;
    }

    public void setSubproduct(int subproduct) {
        this.subproduct = subproduct;
    }

    public String getPriceCurrent() {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        return decimalFormat.format(priceCurrent) + "₩";
    }

    public int getPriceCurrentAsInt() {
        return priceCurrent;
    }

    public String getProductTotalPrice(int quantity) {
        int total = quantity * priceCurrent;

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        return decimalFormat.format(total) + "₩";
    }

    public void setPriceCurrent(int priceCurrent) {
        this.priceCurrent = priceCurrent;
    }

    public String getPriceOriginal() {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        return decimalFormat.format(priceOriginal) + "₩";
    }

    public void setPriceOriginal(int priceOriginal) {
        this.priceOriginal = priceOriginal;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isInfinite() {
        return infinite;
    }

    public void setInfinite(boolean infinite) {
        this.infinite = infinite;
    }

    public boolean isHasOption() {
        return hasOption;
    }

    public void setHasOption(boolean hasOption) {
        this.hasOption = hasOption;
    }

    public ProductImage getImage() {
        return image;
    }

    public void setImage(ProductImage image) {
        this.image = image;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMadeIn() {
        return madeIn;
    }

    public void setMadeIn(String madeIn) {
        this.madeIn = madeIn;
    }

}
