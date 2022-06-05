package com.project.read_pro.model;

import com.google.gson.annotations.SerializedName;

public class Slide {
    @SerializedName("id")
    private int slideId;
    private String title;
    private String subtitle;
    private SlideProduct product;
    private int user;

    public int getSlideId() {
        return slideId;
    }

    public void setSlideId(int slideId) {
        this.slideId = slideId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public SlideProduct getProduct() {
        return product;
    }

    public void setProduct(SlideProduct product) {
        this.product = product;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }
}
