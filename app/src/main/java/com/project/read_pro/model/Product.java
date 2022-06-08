package com.project.read_pro.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Product {
    @SerializedName("id")
    private int productId;
    @SerializedName("name")
    private String title;

    private double price;
    private double discount;
    private String author;
    private String publisher;
    private String genre;
    private String language;
    private String country;
    private String published;
    private String description;
    private String[] image;
    private double averageRating;
    private int numOfReviews;
    private int user;
    private int category;
    private int sold;
    private int views;

    public Product(int productId, String title, float price, float discount, String author, String publisher, String genre, String language, String country, String published, String description, String[] image, float averageRating, int numOfReviews, int user, int category, int sold, int views) {
        this.productId = productId;
        this.title = title;
        this.price = price;
        this.discount = discount;
        this.author = author;
        this.publisher = publisher;
        this.genre = genre;
        this.language = language;
        this.country = country;
        this.published = published;
        this.description = description;
        this.image = image;
        this.averageRating = averageRating;
        this.numOfReviews = numOfReviews;
        this.user = user;
        this.category = category;
        this.sold = sold;
        this.views = views;
    }

    public int getProductId() {
        return productId;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscount() {
        return discount;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getGenre() {
        return genre;
    }

    public String getLanguage() {
        return language;
    }

    public String getCountry() {
        return country;
    }

    public String getPublished() {
        return published;
    }

    public String getDescription() {
        return description;
    }

    public String[] getImage() {
        return image;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public int getNumOfReviews() {
        return numOfReviews;
    }

    public int getUser() {
        return user;
    }

    public int getCategory() {
        return category;
    }

    public int getSold() {
        return sold;
    }

    public int getViews() {
        return views;
    }
}
