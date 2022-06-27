package com.project.read_pro.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.project.read_pro.model.SaveProduct;

import java.util.List;

public class SaveProductResponse {
    @SerializedName("saveProduct")
    @Expose
    private List<SaveProduct> saveProducts;
    private int count;
    private int currentPage;
    private int totalPage;
    private int totalSaveProduct;

    public List<SaveProduct> getSaveProducts() {
        return saveProducts;
    }

    public void setSaveProducts(List<SaveProduct> saveProducts) {
        this.saveProducts = saveProducts;
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

    public int getTotalSaveProduct() {
        return totalSaveProduct;
    }

    public void setTotalSaveProduct(int totalSaveProduct) {
        this.totalSaveProduct = totalSaveProduct;
    }
}
