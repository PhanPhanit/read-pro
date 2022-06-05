package com.project.read_pro.model;

import com.google.gson.annotations.SerializedName;

public class SlideProduct {
    @SerializedName("id")
    private int productId;
    private String[] image;
    private String author;

    public int getProductId() {
        return productId;
    }

    public String[] getImage() {
        return image;
    }

    public String getAuthor() {
        return author;
    }
}
