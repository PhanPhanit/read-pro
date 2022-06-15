package com.project.read_pro.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Product implements Parcelable {
    private int id;
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

    public Product(int id, String title, double price, double discount, String author, String publisher, String genre, String language, String country, String published, String description, String[] image, double averageRating, int numOfReviews, int user, int category, int sold, int views) {
        this.id = id;
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

    protected Product(Parcel in) {
        id = in.readInt();
        title = in.readString();
        price = in.readDouble();
        discount = in.readDouble();
        author = in.readString();
        publisher = in.readString();
        genre = in.readString();
        language = in.readString();
        country = in.readString();
        published = in.readString();
        description = in.readString();
        image = in.createStringArray();
        averageRating = in.readDouble();
        numOfReviews = in.readInt();
        user = in.readInt();
        category = in.readInt();
        sold = in.readInt();
        views = in.readInt();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getImage() {
        return image;
    }

    public void setImage(String[] image) {
        this.image = image;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public int getNumOfReviews() {
        return numOfReviews;
    }

    public void setNumOfReviews(int numOfReviews) {
        this.numOfReviews = numOfReviews;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeDouble(price);
        dest.writeDouble(discount);
        dest.writeString(author);
        dest.writeString(publisher);
        dest.writeString(genre);
        dest.writeString(language);
        dest.writeString(country);
        dest.writeString(published);
        dest.writeString(description);
        dest.writeStringArray(image);
        dest.writeDouble(averageRating);
        dest.writeInt(numOfReviews);
        dest.writeInt(user);
        dest.writeInt(category);
        dest.writeInt(sold);
        dest.writeInt(views);
    }
}
