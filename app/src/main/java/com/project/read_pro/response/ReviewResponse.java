package com.project.read_pro.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.project.read_pro.model.Review;

import java.util.List;

public class ReviewResponse {
    @SerializedName("review")
    @Expose
    private List<Review> reviews;
    private int count;
    private int currentPage;
    private int totalPage;
    private int totalReview;

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
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

    public int getTotalReview() {
        return totalReview;
    }

    public void setTotalReview(int totalReview) {
        this.totalReview = totalReview;
    }
}
