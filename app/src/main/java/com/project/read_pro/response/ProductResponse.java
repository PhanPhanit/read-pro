package com.project.read_pro.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.project.read_pro.model.Product;

import java.util.List;

public class ProductResponse {
    @SerializedName("product")
    @Expose
    private List<Product> products;
    private int count;
    private int currentPage;
    private int totalPage;
    private int totalProduct;

    public List<Product> getProducts() {
        return products;
    }

    public int getCount() {
        return count;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getTotalProduct() {
        return totalProduct;
    }
}
