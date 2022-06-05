package com.project.read_pro.response;

import com.google.gson.annotations.SerializedName;
import com.project.read_pro.model.Slide;

import java.util.List;

public class SlideResponse {
    @SerializedName("slide")
    private List<Slide> slides;

    private int count;
    private int currentPage;
    private int totalPage;
    private int totalSlide;

    public List<Slide> getSlides() {
        return slides;
    }

    public void setSlides(List<Slide> slides) {
        this.slides = slides;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalSlide() {
        return totalSlide;
    }

    public void setTotalSlide(int totalSlide) {
        this.totalSlide = totalSlide;
    }
}
